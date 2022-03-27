package com.emrezorlu.app.demo.tweetstream.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;
import com.emrezorlu.app.demo.tweetstream.message.entity.StatisticEntity;

public class StatisticTestUtil {

	public static List<StatisticEntity> getStatisticEntityList() {
		List<StatisticEntity> list = new ArrayList<>();
		list.add(getStatisticEntity());
		return list;
	}

	public static StatisticEntity getStatisticEntity() {
		return StatisticEntity.builder().createDate(LocalDateTime.now()).durationInSeconds(1L)
				.id(RandomStringUtils.random(2)).keyword(RandomStringUtils.random(5)).tweetPerSeconds(1).tweetSize(10)
				.build();
	}

	public static Statistic getStatistic() {
		return Statistic.builder().createDate(LocalDateTime.now()).durationInSeconds(1L).id(RandomStringUtils.random(2))
				.keyword(RandomStringUtils.random(5)).tweetPerSeconds(1).tweetSize(10).build();
	}

	public static ResponseStatistics getResponseStatistics() {
		List<Statistic> resultList = new ArrayList<>();
		resultList.add(getStatistic());
		resultList.add(getStatistic());
		return ResponseStatistics.builder().resultList(resultList).build();
	}

	public static ResponseSummary getResponseSummary() {
		return ResponseSummary.builder().averageDurationInSeconds(10).averageTweetPerSeconds(10).averageTweetSize(10)
				.keywordOccurrences(Collections.emptyMap()).totalDurationInSeconds(330).totalRuns(99)
				.totalTweetSize(1111).build();
	}

}
