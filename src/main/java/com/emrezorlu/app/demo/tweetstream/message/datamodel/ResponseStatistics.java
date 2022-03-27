package com.emrezorlu.app.demo.tweetstream.message.datamodel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseStatistics {
	List<Statistic> resultList;
}
