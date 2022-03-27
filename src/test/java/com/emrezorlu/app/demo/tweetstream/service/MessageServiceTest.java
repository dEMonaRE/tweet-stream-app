package com.emrezorlu.app.demo.tweetstream.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import com.emrezorlu.app.demo.tweetstream.common.exception.BusinessException;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;
import com.emrezorlu.app.demo.tweetstream.message.entity.StatisticEntity;
import com.emrezorlu.app.demo.tweetstream.message.repository.StatisticRepository;
import com.emrezorlu.app.demo.tweetstream.message.service.MessageServiceImpl;
import com.emrezorlu.app.demo.tweetstream.util.AbstractServiceTest;
import com.emrezorlu.app.demo.tweetstream.util.StatisticTestUtil;
import com.emrezorlu.app.demo.tweetstream.util.TwitterTestUtil;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MessageServiceTest extends AbstractServiceTest {
	@Mock
	StatisticRepository statisticRepository;
	@Spy
	ModelMapper modelMapper;
	@Mock
	Twitter twitter;
	@Mock
	TwitterFactory twitterFactory;

	@InjectMocks
	private MessageServiceImpl messageService;

	@Test
	public void whenGetTweets_ThenReturnData() throws TwitterException {
		QueryResult qr = TwitterTestUtil.getQueryResult();

		when(twitterFactory.getInstance()).thenReturn(twitter);
		when(twitter.search(ArgumentMatchers.any(Query.class))).thenReturn(qr);
		when(statisticRepository.save(ArgumentMatchers.any(StatisticEntity.class))).thenAnswer((i) -> i.getArgument(0));

		Statistic tweets = messageService.getTweets();

		assertNotNull(tweets);

		verify(statisticRepository, times(1)).save(ArgumentMatchers.any(StatisticEntity.class));
	}

	@Test
	public void givenDates_whenGetStatisticListByInterval_thenReturnData() {
		List<StatisticEntity> list = StatisticTestUtil.getStatisticEntityList();

		given(statisticRepository.findByCreateDateBetween(any(), any())).willReturn(list);

		ResponseStatistics response = messageService.getStatisticsByInterval(LocalDateTime.MIN, LocalDateTime.MAX);

		assertNotNull(response);

		verify(statisticRepository, times(1)).findByCreateDateBetween(any(), any());
	}

	@Test
	public void givenDates_whenWrongDate_thenThrowException() {
		when(statisticRepository.findByCreateDateBetween(LocalDateTime.MIN, LocalDateTime.MAX))
				.thenReturn(Collections.emptyList());

		Exception exception = assertThrows(BusinessException.class,
				() -> messageService.getStatisticsByInterval(LocalDateTime.MAX, LocalDateTime.MIN));

		assertEquals("Start Date can not be later than End date!", exception.getMessage());

	}

	@Test
	public void givenKeyword_whenStartStream_thenReturnData() throws InterruptedException {
		Statistic startTweetStream = messageService.startTweetStream(RandomStringUtils.random(10));
		assertNotNull(startTweetStream);
	}

	@Test
	public void whenGetSummary_thenReturnData() throws InterruptedException {
		when(statisticRepository.findAll()).thenReturn(StatisticTestUtil.getStatisticEntityList());
		ResponseSummary summaryReport = messageService.getSummaryReport();
		assertNotNull(summaryReport);
		assertEquals(1, summaryReport.getTotalRuns());
		verify(statisticRepository, times(1)).findAll();
	}

}
