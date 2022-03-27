package com.emrezorlu.app.demo.tweetstream.message.service;

import java.time.LocalDateTime;

import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;

public interface MessageService {

	Statistic getTweets();

	ResponseStatistics getStatisticsByInterval(LocalDateTime from, LocalDateTime to);

	Statistic startTweetStream(String keyword) throws InterruptedException;

	ResponseSummary getSummaryReport();

}
