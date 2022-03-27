package com.emrezorlu.app.demo.tweetstream.common.datamodel;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
	private T data;
	private HttpStatus httpStatus;
}
