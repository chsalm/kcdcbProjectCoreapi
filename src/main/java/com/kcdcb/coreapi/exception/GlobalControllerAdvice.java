package com.kcdcb.coreapi.exception;

import com.kcdcb.coreapi.config.component.KcdCbSuccess;
import com.kcdcb.coreapi.exception.error.KcdCbError;
import com.kcdcb.coreapi.exception.error.ErrorResponse;
import com.kcdcb.coreapi.config.component.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<BaseResponse> noSuchElExHandler(NoSuchElementException e) {
        return new ResponseEntity<BaseResponse>(new BaseResponse(KcdCbSuccess.GET_SUCCESS), HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpReqMethodNotSupportExHandler(HttpRequestMethodNotSupportedException e) {
        log.error(e.getClass().getName(), e);
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(KcdCbError.HTTP_NOT_SUPPORTED_METHOD_EXCEPTION), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindExHandler(BindException e) {
        log.error(e.getClass().getName(), e);
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KcdCbException.class)
    public ResponseEntity<ErrorResponse> kcdCbExHandler(KcdCbException e) {
        log.error(e.getClass().getName(), e);
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getKcdCbError()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exHandler(Exception e) {
        log.error(e.getClass().getName(), e);
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(KcdCbError.SYSTEM_EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
