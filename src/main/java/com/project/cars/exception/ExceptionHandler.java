package com.project.cars.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<?> errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<?> errorBadRequest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}

}
