package com.bootcamp.santiagomed93.hotelApi.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler{
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> hadleInvalidRequest(ConstraintViolationException ex){
		System.out.println("1 - " + ex.getErrorCode());
		System.out.println(ex.getMessage());
		ApiError apiError = new ApiError();
		switch (ex.getErrorCode()) {
			case 1062:
				apiError.setStatus(HttpStatus.CONFLICT);
				apiError.setMessage("Register already exists");
				break;
				
			case 1048:
				apiError.setStatus(HttpStatus.CONFLICT);
				apiError.setMessage("Some Fields can not be null");
				break;
				
			default:
				apiError.setStatus(HttpStatus.BAD_REQUEST);
				break;
		}
		return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
        
	}
	
	@ExceptionHandler(BindException.class)
	public final ResponseEntity<Object> hadleIna(Exception ex){
		System.out.println("2" + ex);
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> hadleWrogParameter(MethodArgumentNotValidException ex){
		System.out.println("3" + ex);
		List<String> errors = new ArrayList<String>();
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
		ApiError apiError = new ApiError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Error de Validacion");
		apiError.setErrors(errors);
		
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
        
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<Object> hadleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
		ApiError apiError = new ApiError();
		apiError.setMessage("Method " + ex.getMethod() + " not allowed");
		apiError.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(apiError,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<Object> hadleEmptyResultData(EmptyResultDataAccessException ex){
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public final ResponseEntity<Object> hadleInvalidDataAccess(InvalidDataAccessApiUsageException ex){
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getCause().toString());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public final ResponseEntity<Object> handleMissingRequestParameterException(MissingServletRequestParameterException ex){
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> hadleJsonParse(Exception ex){
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	

}
