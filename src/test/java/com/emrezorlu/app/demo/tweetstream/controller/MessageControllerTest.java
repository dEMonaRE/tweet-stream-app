package com.emrezorlu.app.demo.tweetstream.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;
import com.emrezorlu.app.demo.tweetstream.message.service.MessageService;
import com.emrezorlu.app.demo.tweetstream.util.AbstractServiceTest;
import com.emrezorlu.app.demo.tweetstream.util.StatisticTestUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MessageControllerTest extends AbstractServiceTest {
	@InjectMocks
	private MessageController controller;

	@Mock
	private MessageService messageService;

	private ObjectMapper mapper;
	private ObjectWriter ow;
	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

		ow = mapper.writer().withDefaultPrettyPrinter();

	}

	@Test
	public void whenGetTweets_thenReturnData() throws Exception {
		Statistic stats = StatisticTestUtil.getStatistic();
		when(messageService.getTweets()).thenReturn(stats);
		MvcResult andExpect = mockMvc.perform(get("/message" + "/getTweets").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

	@Test
	public void whenGetStatisticsByInterval_thenReturnData() throws Exception {
		ResponseStatistics response = StatisticTestUtil.getResponseStatistics();
		when(messageService.getStatisticsByInterval(any(), any())).thenReturn(response);
		MvcResult andExpect = mockMvc
				.perform(get("/message" + "/getStatisticsByInterval").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

	@Test
	public void whenStartStream_thenReturnData() throws Exception {
		Statistic stats = StatisticTestUtil.getStatistic();
		when(messageService.startTweetStream(anyString())).thenReturn(stats);
		MvcResult andExpect = mockMvc.perform(
				get("/message" + "/startStream").param("keyword", "emrezorlu").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

	@Test
	public void whenGetSummaryReport_thenReturnData() throws Exception {
		ResponseSummary response = StatisticTestUtil.getResponseSummary();
		when(messageService.getSummaryReport()).thenReturn(response);
		MvcResult andExpect = mockMvc.perform(get("/message" + "/getSummary").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

}
