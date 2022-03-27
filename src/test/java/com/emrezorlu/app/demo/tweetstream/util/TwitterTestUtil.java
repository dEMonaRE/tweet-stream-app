package com.emrezorlu.app.demo.tweetstream.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.emrezorlu.app.demo.tweetstream.message.datamodel.Author;
import com.emrezorlu.app.demo.tweetstream.message.datamodel.Message;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class TwitterTestUtil {

	public static QueryResult getQueryResult() {
		QueryResult qr = new QueryResult() {

			@Override
			public RateLimitStatus getRateLimitStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getAccessLevel() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public Query nextQuery() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public List<Status> getTweets() {
				List<Status> tweets = new ArrayList<>();
				tweets.add(getStatus());
				return tweets;
			}

			@Override
			public long getSinceId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getRefreshURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getQuery() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getMaxId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public double getCompletedIn() {
				// TODO Auto-generated method stub
				return 1;
			}
		};
		return qr;
	}

	public static User getUser() {

		User user = new User() {

			@Override
			public RateLimitStatus getRateLimitStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getAccessLevel() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int compareTo(User o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isVerified() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isTranslator() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isShowAllInlineMedia() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isProtected() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isProfileUseBackgroundImage() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isProfileBackgroundTiled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isGeoEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isFollowRequestSent() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDefaultProfileImage() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDefaultProfile() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isContributorsEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String[] getWithheldInCountries() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getUtcOffset() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public URLEntity getURLEntity() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getTimeZone() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getStatusesCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Status getStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getScreenName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileTextColor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileSidebarFillColor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileSidebarBorderColor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileLinkColor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileImageURLHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileImageURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerRetinaURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerMobileURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerMobileRetinaURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerIPadURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBannerIPadRetinaURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBanner600x200URL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBanner300x100URL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBanner1500x500URL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBackgroundImageUrlHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBackgroundImageURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProfileBackgroundColor() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getOriginalProfileImageURLHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getOriginalProfileImageURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getMiniProfileImageURLHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getMiniProfileImageURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLocation() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getListedCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getLang() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getFriendsCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getFollowersCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getFavouritesCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getEmail() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public URLEntity[] getDescriptionURLEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Date getCreatedAt() {
				// TODO Auto-generated method stub
				return new Date();
			}

			@Override
			public String getBiggerProfileImageURLHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getBiggerProfileImageURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String get400x400ProfileImageURLHttps() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String get400x400ProfileImageURL() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return user;
	}

	public static Status getStatus() {
		Status status = new Status() {

			@Override
			public UserMentionEntity[] getUserMentionEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public URLEntity[] getURLEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public SymbolEntity[] getSymbolEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MediaEntity[] getMediaEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HashtagEntity[] getHashtagEntities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public RateLimitStatus getRateLimitStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getAccessLevel() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int compareTo(Status o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isTruncated() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRetweetedByMe() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRetweeted() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRetweet() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isPossiblySensitive() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isFavorited() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String[] getWithheldInCountries() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public User getUser() {
				// TODO Auto-generated method stub
				return TwitterTestUtil.getUser();
			}

			@Override
			public String getText() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getSource() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Scopes getScopes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Status getRetweetedStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getRetweetCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public URLEntity getQuotedStatusPermalink() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getQuotedStatusId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Status getQuotedStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Place getPlace() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLang() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getInReplyToUserId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getInReplyToStatusId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getInReplyToScreenName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public GeoLocation getGeoLocation() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getFavoriteCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getDisplayTextRangeStart() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getDisplayTextRangeEnd() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getCurrentUserRetweetId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Date getCreatedAt() {
				// TODO Auto-generated method stub
				return new Date();
			}

			@Override
			public long[] getContributors() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return status;
	}

	public static Author getAuthor() {
		Author auth = new Author();
		auth.setCreatedAt(new Date());
		auth.setCreationDate(new Date());
		auth.setId(1L);
		auth.setName("auth");
		auth.setScreenName("auth");
		return auth;
	}

	public static Message getMessage() {
		Message msg = new Message();
		msg.setCreatedAt(new Date());
		msg.setCreationDate(new Date());
		msg.setAuthor(getAuthor());
		msg.setId(1L);
		msg.setText("msg content");
		return msg;
	}

}
