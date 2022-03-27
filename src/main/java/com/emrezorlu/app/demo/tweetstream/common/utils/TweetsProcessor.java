package com.emrezorlu.app.demo.tweetstream.common.utils;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import twitter4j.Status;
import twitter4j.User;

public class TweetsProcessor {

	private Stream<User> sortedUsers;

	private Map<Long, List<Status>> sortedMessagesGroupedByUser;

	public void execute(final List<Status> tweets) {
		sortedMessagesGroupedByUser = produceSortedMessagesGroupedByUser(tweets);
		this.sortedUsers = produceSortedUsers(sortedMessagesGroupedByUser);
	}

	private Map<Long, List<Status>> produceSortedMessagesGroupedByUser(final List<Status> tweets) {
		// messages grouped by user ID
		Map<Long, List<Status>> messagesGroupedByUser = tweets.stream()
				.collect(groupingBy(status -> status.getUser().getId()));

		// The messages per user should also be sorted chronologically, ascending
		messagesGroupedByUser.keySet().forEach(user -> messagesGroupedByUser.put(user, messagesGroupedByUser.get(user)
				.stream().sorted(Comparator.comparing(Status::getCreatedAt)).collect(Collectors.toList())));
		return messagesGroupedByUser;
	}

	private Stream<User> produceSortedUsers(Map<Long, List<Status>> messagesGroupedByUser) {
		// get list of users based on user ID list
		final List<User> users = new ArrayList<>();
		messagesGroupedByUser.values()
				.forEach(msgs -> msgs.stream().findAny().ifPresent(msg -> users.add(msg.getUser())));

		// users sorted to createdAt ASC
		return users.stream().sorted(Comparator.comparing(User::getCreatedAt));
	}

	public Map<Long, List<Status>> getSortedMessagesGroupedByUser() {
		return sortedMessagesGroupedByUser;
	}

	public Stream<User> getSortedUsers() {
		return sortedUsers;
	}
}
