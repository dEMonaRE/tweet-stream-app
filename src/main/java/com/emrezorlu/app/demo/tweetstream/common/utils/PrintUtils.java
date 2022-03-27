package com.emrezorlu.app.demo.tweetstream.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.emrezorlu.app.demo.tweetstream.message.datamodel.Author;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Message;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.User;

@Slf4j
@NoArgsConstructor
public class PrintUtils {
	private static final ZoneId zone = ZoneId.of("GMT+3");

	public static LocalDateTime convertToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zone);
	}

	public static Long convertToEpoch(LocalDateTime localDateTime) {
		return localDateTime.toEpochSecond(ZoneOffset.of("GMT+3"));
	}

	public static void printUserAndMessages(User user, List<Status> tweets) {
		printUser(user);
		tweets.forEach(PrintUtils::printTweet);
	}

	private static void printTweet(Status tweet) {
		ModelMapper modelMapper = new ModelMapper();
		Message msg = modelMapper.map(tweet, Message.class);
		msg.setCreationDate(msg.getCreatedAt());
		Author auth = new Author(tweet.getUser());
		auth.setCreationDate(auth.getCreatedAt());
		msg.setAuthor(auth);
		log.info(msg.toString());
	}

	private static void printUser(User user) {
		ModelMapper modelMapper = new ModelMapper();
		Author auth = modelMapper.map(user, Author.class);
		auth.setCreationDate(auth.getCreatedAt());
		log.info(auth.toString());
	}
}
