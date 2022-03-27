
package com.emrezorlu.app.demo.tweetstream.message.datamodel;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import twitter4j.User;

@Data
@NoArgsConstructor
public class Author {
	private Long id;
	private Date createdAt;
	private Long creationDate;
	private String name;
	private String screenName;

	public void setCreationDate(Date createdAt) {
		this.creationDate = createdAt.toInstant().getEpochSecond();
	}

	public Author(User user) {
		this.id = user.getId();
		this.createdAt = user.getCreatedAt();
		this.creationDate = user.getCreatedAt().toInstant().getEpochSecond();
		this.name = user.getName();
		this.screenName = user.getScreenName();
	}
}
