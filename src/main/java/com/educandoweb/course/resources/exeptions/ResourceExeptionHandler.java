package com.educandoweb.course.resources.exeptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exeptions.DatabaseExeption;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExeptionHandler {

	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<StandardError> resouceNotFound(ResourceNotFoundExeption e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value() , error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseExeption.class)
	public ResponseEntity<StandardError> database(DatabaseExeption e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(),status.value() , error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
