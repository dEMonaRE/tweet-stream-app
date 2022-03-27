package com.emrezorlu.app.demo.tweetstream.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emrezorlu.app.demo.tweetstream.common.utils.TweetsProcessor;

import twitter4j.Status;
import twitter4j.User;

class TweetsProcessorTest {
	@Mock
	Stream<User> sortedUsers;
	@Mock
	Map<Long, List<Status>> sortedMessagesGroupedByUser;
	@InjectMocks
	TweetsProcessor tweetsProcessor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenExecute_thenDoNothing() {
		List<Status> tweets = TwitterTestUtil.getQueryResult().getTweets();
		tweetsProcessor.execute((tweets));
		assertNotNull(tweets);
	}
}
