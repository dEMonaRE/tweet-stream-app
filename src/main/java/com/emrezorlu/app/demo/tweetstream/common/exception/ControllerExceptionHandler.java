package com.emrezorlu.app.demo.tweetstream.common.exception;

import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emrezorlu.app.demo.tweetstream.common.datamodel.ResponseError;
import com.emrezorlu.app.demo.tweetstream.common.enums.ErrorCode;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.TwitterException;

@Slf4j
@ControllerAdvice
@NoArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = BusinessException.class)
	protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		ResponseError body = new ResponseError(ex.getMessage(), ex.getErrorCode().getCode());
		HttpStatus httpStatus = ErrorCode.getHttpStatus(ex.getErrorCode());
		log.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
	}

	@ExceptionHandler(value = RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		ResponseError body = new ResponseError(ex.getMessage(), ErrorCode.GENERIC.getCode());
		HttpStatus httpStatus = ErrorCode.getHttpStatus(ErrorCode.GENERIC);
		log.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
	}

	@ExceptionHandler(value = ValidationException.class)
	protected ResponseEntity<Object> handleRuntimeException(ValidationException ex, WebRequest request) {
		ResponseError body = new ResponseError(ex.getMessage(), ErrorCode.INVALID_ARGUMENTS.getCode());
		HttpStatus httpStatus = ErrorCode.getHttpStatus(ErrorCode.INVALID_ARGUMENTS);
		log.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
	}

	@ExceptionHandler(value = InterruptedException.class)
	protected ResponseEntity<Object> handleInterruptedException(InterruptedException ex, WebRequest request) {
		ResponseError body = new ResponseError(ex.getMessage(), ErrorCode.COUNTDOWN_THREAD_ERROR.getCode());
		HttpStatus httpStatus = ErrorCode.getHttpStatus(ErrorCode.COUNTDOWN_THREAD_ERROR);
		log.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
	}

	@ExceptionHandler(value = TwitterException.class)
	protected ResponseEntity<Object> handleTwitterException(TwitterException ex, WebRequest request) {
		ResponseError body = new ResponseError(ex.getMessage(), ErrorCode.TWITTER_HAS_GONE.getCode());
		HttpStatus httpStatus = ErrorCode.getHttpStatus(ErrorCode.TWITTER_HAS_GONE);
		log.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
	}

}
