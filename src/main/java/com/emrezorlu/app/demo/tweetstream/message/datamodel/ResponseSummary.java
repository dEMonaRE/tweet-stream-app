package com.emrezorlu.app.demo.tweetstream.message.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSummary {
  private Integer totalRuns;
  private Integer totalTweetSize;
  private double averageTweetSize;
  private double averageDurationInSeconds;
  private double totalDurationInSeconds;
  private double averageTweetPerSeconds;
  private Map<String, Long> keywordOccurrences;
}
