package com.emrezorlu.app.demo.tweetstream.controller;

import java.time.LocalDateTime;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emrezorlu.app.demo.tweetstream.common.datamodel.ResponseDTO;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseStatistics;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.ResponseSummary;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Statistic;
import com.emrezorlu.app.demo.tweetstream.message.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Get Tweets & Statistics & More...")
@RestController
@RequestMapping(path = "/message", produces = "application/json")
@AllArgsConstructor
public class MessageController {
	@Autowired
	MessageService messageService;

	@Operation(summary = "getTweets")
	@GetMapping(path = "/getTweets")
	public ResponseDTO<Statistic> getTweets() {
		return new ResponseDTO<>(messageService.getTweets(), HttpStatus.OK);
	}

	@Operation(summary = "getStatistics by interval  2022-03-21T01:00:00.000-01:00 ")
	@GetMapping(path = "/getStatisticsByInterval")
	public ResponseDTO<ResponseStatistics> getStatisticsByInterval(
			@PathParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
			@PathParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
		return new ResponseDTO<>(messageService.getStatisticsByInterval(from, to), HttpStatus.OK);

	}

	@Operation(summary = "startStream v2")
	@GetMapping(path = "/startStream")
	public ResponseDTO<Statistic> startStream(@RequestParam(value = "keyword", required = false) String keyword)
			throws InterruptedException {
		return new ResponseDTO<>(messageService.startTweetStream(keyword), HttpStatus.OK);
	}

	@Operation(summary = "Summary Report")
	@GetMapping(path = "/getSummary")
	public ResponseDTO<ResponseSummary> getSummary() {
		return new ResponseDTO<>(messageService.getSummaryReport(), HttpStatus.OK);
	}

}
