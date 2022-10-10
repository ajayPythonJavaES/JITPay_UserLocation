package com.jitpay.user.location.exception;

import java.time.format.DateTimeParseException;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class JITPayExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		ApiException apiException = new ApiException(HttpStatus.NOT_FOUND);
		apiException.setDebugMessage(ex.getLocalizedMessage());
		apiException.setMessage(ex.getMessage());
		return buildResponseEntity(apiException);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> handleParseException(DateTimeParseException ex) {
		ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST);
		apiException.setDebugMessage(ex.getLocalizedMessage());
		apiException.setMessage(ex.getMessage() + ". Please check the date format specified in the request");
		return buildResponseEntity(apiException);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiException apiException) {
		return new ResponseEntity<>(apiException, apiException.getStatus());
	}

}