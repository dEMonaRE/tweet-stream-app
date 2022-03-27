
package com.emrezorlu.app.demo.tweetstream.message.datamodel;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
	private Long id;
	private Date createdAt;
	private Long creationDate;
	private String text;
  private Author author;

	public void setCreationDate(Date createdAt) {
		this.creationDate = createdAt.toInstant().getEpochSecond();
	}
}
