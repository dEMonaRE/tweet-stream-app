package com.emrezorlu.app.demo.tweetstream.util;

import static com.emrezorlu.app.demo.tweetstream.common.utils.PrintUtils.printUserAndMessages;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import com.emrezorlu.app.demo.tweetstream.common.utils.PrintUtils;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Author;

import twitter4j.Status;
import twitter4j.User;

public class PrintUtilsTest extends AbstractServiceTest {
	@InjectMocks
	PrintUtils printUtils;
	@Mock
	private Author author;
	@Mock
	ModelMapper modelMapper;
	@Mock
	private Status status;

	@Test
	public void whenPrint_thenLog() {
		List<Status> statusList = new ArrayList<>();
		statusList.add(TwitterTestUtil.getStatus());
		User user = TwitterTestUtil.getUser();
		Author auth = TwitterTestUtil.getAuthor();
		TwitterTestUtil.getMessage();
		when(modelMapper.map(ArgumentMatchers.any(User.class), any())).thenReturn(auth);
		when(status.getUser()).thenReturn(user);
		when(modelMapper.map(ArgumentMatchers.any(User.class), any())).thenReturn(auth);
		printUserAndMessages(user, statusList);
		verify(status, atMostOnce()).getUser();
		assertNotNull(auth);
	}

}
