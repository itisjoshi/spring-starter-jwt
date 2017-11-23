package com.beanhq.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.beanhq.utils.Constants;

/**
 * Created by Prathap Manohar Joshi
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = Constants.RESOURCE_NOT_FOUND_ERROR_MESSAGE)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2718474361991018623L;

}