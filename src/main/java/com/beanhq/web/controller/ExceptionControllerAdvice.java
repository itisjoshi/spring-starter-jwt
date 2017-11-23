package com.beanhq.web.controller;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.beanhq.utils.Constants;
import com.beanhq.web.exception.ResourceNotFoundException;
import com.beanhq.web.model.error.ErrorCode;
import com.beanhq.web.model.error.ErrorResponse;

/**
 * Created by Prathap Manohar Joshi
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExceptionControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(
    		MethodArgumentNotValidException exception) {
		 BindingResult bindingResult = exception.getBindingResult();
	        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
	        ErrorResponse errorResponse = new ErrorResponse();

	        errorResponse.setFieldErrors(fieldErrors);
            errorResponse.setErrorCode(ErrorCode.FIELD_INVALID);
            errorResponse.setErrorMessage(Constants.INVALID_FIELD_ERROR_MESSAGE);
	        return errorResponse;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException (ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.RESOURCE_NOT_FOUND);
        errorResponse.setErrorMessage(Constants.RESOURCE_NOT_FOUND_ERROR_MESSAGE);
        exception.printStackTrace();
        errorResponse.setDevErrorMessage(exception.getMessage());
        return errorResponse;
    }
	
	@ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse accessDeniedException (AccessDeniedException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.ACCESS_DENEID);
        errorResponse.setErrorMessage(Constants.RESOURCE_NOT_FOUND_ERROR_MESSAGE);
        exception.printStackTrace();
        errorResponse.setDevErrorMessage(exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorResponse handleException (Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.SERVICE_UNAVAILABLE);
        errorResponse.setErrorMessage(Constants.SERVICE_UNAVAILABLE_ERROR_MESSAGE);
        exception.printStackTrace();
        errorResponse.setDevErrorMessage(exception.getMessage());
        return errorResponse;
    }
}