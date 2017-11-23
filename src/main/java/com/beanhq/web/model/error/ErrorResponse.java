package com.beanhq.web.model.error;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by Prathap Manohar Joshi
 */
public class ErrorResponse {

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String devErrorMessage;

    public String getDevErrorMessage() {
        return devErrorMessage;
    }

    public void setDevErrorMessage(String devErrorMessage) {
        this.devErrorMessage = devErrorMessage;
    }

    private List<FieldError> fieldErrors;

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}