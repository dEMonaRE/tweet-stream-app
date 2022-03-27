package com.emrezorlu.app.demo.tweetstream.message.datamodel;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistic {
	private String id;
	private String keyword;
	private LocalDateTime createDate;
	private Integer tweetSize;
	private double durationInSeconds;
	private double tweetPerSeconds;
}
