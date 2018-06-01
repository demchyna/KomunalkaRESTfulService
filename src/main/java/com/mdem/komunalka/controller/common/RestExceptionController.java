package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.exception.*;
import com.mdem.komunalka.model.common.ErrorInfo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionController {

    private Logger logger;

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(ConflictDataException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorInfo conflictDataException(HttpServletRequest request, ConflictDataException exception) {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.CONFLICT.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorInfo dataNotFoundException(HttpServletRequest request, DataNotFoundException exception) {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(NoDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorInfo noDataException(HttpServletRequest request, NoDataException exception) {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorInfo incorrectPasswordException(HttpServletRequest request, IncorrectPasswordException exception) {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorInfo resourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorInfo methodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception)   {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.METHOD_NOT_ALLOWED.value(), errorURL, errorMessage);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorInfo mediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException exception)   {
        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();

        logger.error(errorMessage, exception);

        return new ErrorInfo(HttpStatus.METHOD_NOT_ALLOWED.value(), errorURL, errorMessage);
    }


}
