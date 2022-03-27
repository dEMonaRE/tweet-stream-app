package com.emrezorlu.app.demo.tweetstream.message.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import com.emrezorlu.app.demo.tweetstream.common.enums.ErrorCode;
import com.emrezorlu.app.demo.tweetstream.common.exception.BusinessException;
import com.emrezorlu.app.demo.tweetstream.common.utils.PrintUtils;
import com.emrezorlu.app.demo.tweetstream.common.utils.TweetsProcessor;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;
import com.emrezorlu.app.demo.tweetstream.message.entity.StatisticEntity;
import com.emrezorlu.app.demo.tweetstream.message.repository.StatisticRepository;

import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
	@Autowired
	StatisticRepository statisticRepository;
	@Autowired
	ModelMapper modelMapper;

	@Value("${message.track}")
	private String messageKeyword;
	@Value("${message.max.count}")
	private int maxMessageCount;
	@Value("${message.streaming.max.duration}")
	private int maxStreamingDuration;

	private CountDownLatch latch;
	private StatusListener listener;

	@Override
	public Statistic getTweets() {
		log.debug("emre");

		TwitterFactory tf = new TwitterFactory();
		Twitter twitter = tf.getInstance();

		List<Status> tweets = new ArrayList<>();
		long durationInSeconds = 1;

		try {
			durationInSeconds = fetchFilteredMessages(twitter, tweets, messageKeyword);
		} catch (TwitterException te) {
			log.error("Failed to search tweets: ", te);
		}

		double tweetPerSeconds = tweets.size() / durationInSeconds;
		log.info("Number of messages per second: " + tweetPerSeconds);

		StatisticEntity entity = StatisticEntity.builder().createDate(LocalDateTime.now())
				.durationInSeconds(durationInSeconds).tweetSize(tweets.size()).tweetPerSeconds(tweetPerSeconds)
				.keyword(messageKeyword).build();

		statisticRepository.save(entity);

		processTweets(tweets);

		return modelMapper.map(entity, Statistic.class);
	}

	private void processTweets(List<Status> tweets) {

		TweetsProcessor processor = new TweetsProcessor();

		processor.execute(Collections.unmodifiableList(tweets));

		Stream<User> sortedUsers = processor.getSortedUsers();

		Map<Long, List<Status>> sortedMessagesGroupedByUser = processor.getSortedMessagesGroupedByUser();

		sortedUsers.forEachOrdered(
				user -> PrintUtils.printUserAndMessages(user, sortedMessagesGroupedByUser.get(user.getId())));
	}

	private long fetchFilteredMessages(final Twitter twitter, final List<Status> outputTweets, final String keyword)
			throws TwitterException {

		log.info("maxStreamingDuration : " + maxStreamingDuration + " seconds");
		Query query = new Query(keyword);
		query.setCount(maxMessageCount);
		QueryResult result;

		long beginTimeStampInSeconds = Instant.now().getEpochSecond();
		long currentTimeStampSeconds;
		long diffTimeStampInSeconds;

		synchronized (this) {
			do {

				result = twitter.search(query);

				outputTweets.addAll(result.getTweets());

				currentTimeStampSeconds = Instant.now().getEpochSecond();
				diffTimeStampInSeconds = currentTimeStampSeconds - beginTimeStampInSeconds;
				log.info("listening tweet stream : " + diffTimeStampInSeconds + " sec");
			} while ((outputTweets.size() <= maxMessageCount) && (diffTimeStampInSeconds <= maxStreamingDuration));
		}

		log.info("total time elapsed : " + diffTimeStampInSeconds + " sec");
		return diffTimeStampInSeconds;
	}

	@Override
	public ResponseStatistics getStatisticsByInterval(LocalDateTime from, LocalDateTime to) {
		if (from == null) {
			throw new BusinessException("Start Date can not be null!");
		} else if (to == null) {
			throw new BusinessException("End Date can not be null!");
		}

		if (from.isAfter(to)) {
			throw new BusinessException("Start Date can not be later than End date!");
		}

		List<StatisticEntity> entities = statisticRepository.findByCreateDateBetween(from, to);

		if (CollectionUtils.isEmpty(entities)) {
			throw new BusinessException(String.format("No Transactions Found from %s to %s", from, to),
					ErrorCode.NO_TRANSACTIONS_FOR_PERIOD);
		}

		List<Statistic> resultList = entities.stream()
				.map(objectEntity -> modelMapper.map(objectEntity, Statistic.class)).collect(Collectors.toList());

		return ResponseStatistics.builder().resultList(resultList).build();
	}

	@Override
	public Statistic startTweetStream(String keyword) throws InterruptedException {

		createCountDownLatch();
		AtomicInteger counter = new AtomicInteger(0);
		List<Status> tweets = new ArrayList<>();

		StatusListener statusListener = getStatusListener(latch, counter, tweets);

		// gets application props if blank
		if (StringUtils.isBlank(keyword)) {
			keyword = messageKeyword;
		}

		FilterQuery trackFilterQuery = createTrackerFilterQuery(keyword);

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

		twitterStream.addListener(statusListener);

		StopWatch sw = new StopWatch();

		sw.start();

		twitterStream.filter(trackFilterQuery);

		boolean stopped = latch.await(maxStreamingDuration, TimeUnit.SECONDS);
		sw.stop();

		stopTwitterStream(twitterStream, statusListener);

		log.info("twitterStream destroyed:{} elapsed time: {}, tweet count: {}", stopped, sw.getTotalTimeMillis(),
				counter.get());

		double tweetPerSeconds = counter.get() / sw.getTotalTimeSeconds();

		StatisticEntity entity = StatisticEntity.builder().createDate(LocalDateTime.now())
				.durationInSeconds(sw.getTotalTimeSeconds()).tweetSize(counter.get()).tweetPerSeconds(tweetPerSeconds)
				.keyword(keyword).build();

		statisticRepository.save(entity);

		processTweets(tweets);

		return modelMapper.map(entity, Statistic.class);
	}

	private void createCountDownLatch() {
		if (latch != null) {
			return;
		}
		latch = new CountDownLatch(maxMessageCount);
	}

	private @NotNull FilterQuery createTrackerFilterQuery(String... filters) {
		FilterQuery tweetFilterQuery = new FilterQuery();
		tweetFilterQuery.track(filters);
		return tweetFilterQuery;
	}

	private void stopTwitterStream(TwitterStream twitterStream, StatusListener statusListener) {
		twitterStream.removeListener(statusListener);
		twitterStream.shutdown();
	}

	private StatusListener getStatusListener(CountDownLatch latch, AtomicInteger counter, List<Status> list) {
		if (listener != null) {
			return listener;
		}

		return new StatusListener() {
			public void onStatus(Status status) {
				log.info("TweetStream listening");
				latch.countDown();

				if (counter.incrementAndGet() <= maxMessageCount) {
					list.add(status);
				}
			}

			@Override
			public void onException(Exception ex) {
				log.error("TweetStream onException: ", ex);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				log.info("TweetStream onDeletionNotice");
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				log.info("TweetStream onTrackLimitationNotice");
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				log.info("TweetStream onScrubGeo");
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				log.info("TweetStream onStallWarning");
			}
		};
	}

	@Override
	public ResponseSummary getSummaryReport() {
		List<StatisticEntity> statisticList = statisticRepository.findAll();

		ResponseSummary response = new ResponseSummary();

		if (CollectionUtils.isEmpty(statisticList)) {
			return response;
		}

		Map<String, Long> occurrences = statisticList.stream()
				.collect(Collectors.groupingBy(s -> s.getKeyword(), Collectors.counting()));
		response.setKeywordOccurrences(occurrences);

		double averageTweetPerSeconds = statisticList.stream().mapToDouble(StatisticEntity::getTweetPerSeconds)
				.average().orElse(Double.NaN);
		response.setAverageTweetPerSeconds(averageTweetPerSeconds);

		double averageDurationInSeconds = statisticList.stream().mapToDouble(StatisticEntity::getDurationInSeconds)
				.average().orElse(Double.NaN);
		response.setAverageDurationInSeconds(averageDurationInSeconds);

		double averageTweetSize = statisticList.stream().mapToDouble(StatisticEntity::getTweetSize).average()
				.orElse(Double.NaN);
		response.setAverageTweetSize(averageTweetSize);

		Integer totalTweetSize = statisticList.stream().map(StatisticEntity::getTweetSize).reduce(0, Integer::sum);
		response.setTotalTweetSize(totalTweetSize);

		response.setTotalRuns(statisticList.size());

		double totalDuration = statisticList.stream().mapToDouble(StatisticEntity::getDurationInSeconds).sum();
		response.setTotalDurationInSeconds(totalDuration);

		return response;
	}
}
