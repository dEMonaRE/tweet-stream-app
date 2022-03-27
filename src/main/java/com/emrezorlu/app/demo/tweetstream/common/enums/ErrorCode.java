package com.emrezorlu.app.demo.tweetstream.common.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	GENERIC(1000), //
	INVALID_ARGUMENTS(1003), //
	COUNTDOWN_THREAD_ERROR(1100), //
	TWITTER_HAS_GONE(1200), //
	NO_TRANSACTIONS_FOR_PERIOD(1201), //
	INTERNAL_ERROR(2000);

	private final Integer code;

	ErrorCode(Integer code) {
		this.code = code;
	}

	public static HttpStatus getHttpStatus(ErrorCode errorCode) {
		if (errorCode.code > 1000 && errorCode.code < 1100) {
			return HttpStatus.BAD_REQUEST;
		} else if (errorCode.code >= 2000 && errorCode.code < 3000) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} else if (errorCode.code >= 1100 && errorCode.code < 1200) {
			return HttpStatus.SEE_OTHER;
		} else if (errorCode.code >= 1200 && errorCode.code < 2000) {
			return HttpStatus.NOT_FOUND;
		} else {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
