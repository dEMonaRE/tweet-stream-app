package com.emrezorlu.app.demo.tweetstream.message.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document("Statistic")
public class StatisticEntity {
	@Id
	private String id;
	private String keyword;
	private LocalDateTime createDate;
	private Integer tweetSize;
	private double durationInSeconds;
	private double tweetPerSeconds;

}
