package com.concretepage.controller;
import java.time.ZonedDateTime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.concretepage.entity.SignUpStatus;

public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<SignUpStatus> handleError(Exception ex) {
		LOGGER.info("start");
		
			LOGGER.info("handleError() execution started!");
			LOGGER.error(ex.getMessage(), ex);
			SignUpStatus error = new SignUpStatus();
			LOGGER.debug("Error -> {}", error);
			ResponseEntity<SignUpStatus> response = null;

			if (ex instanceof ConstraintViolationException) {
				ConstraintViolationException constraintException = (ConstraintViolationException) ex;
				Set<ConstraintViolation<?>> violations = constraintException.getConstraintViolations();
				String errorMessage = "Message: ";
				for (ConstraintViolation<?> constraintViolation : violations) {
					errorMessage += constraintViolation.getMessageTemplate() + ", ";
				}
				errorMessage = errorMessage.substring(0, errorMessage.length()-2);
				error.setError(errorMessage);
				response = new ResponseEntity<SignUpStatus>(error, HttpStatus.BAD_REQUEST);
			} 
			else if (ex instanceof MethodArgumentNotValidException) 
			{
				MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
				String message = "";
				List<FieldError> errors = exception.getBindingResult().getFieldErrors();
				for (FieldError error1 : errors) {
					message += error1.getDefaultMessage() + ", ";
				}
				error.setError(ex.getMessage());
				return new ResponseEntity<SignUpStatus>(error, HttpStatus.BAD_REQUEST);

			}
			else if (ex instanceof AuthenticationException) {
				AuthenticationException au = (AuthenticationException) ex;
				error.setError(au.getLocalizedMessage());
				response = new ResponseEntity<SignUpStatus>(error, HttpStatus.BAD_REQUEST);
		 }
			
			else {
				
				error.setError("System Error. Please contact Administrator");
				response = new ResponseEntity<SignUpStatus>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			LOGGER.info("handleError() execution is completed!");
			return response;
	}
}
