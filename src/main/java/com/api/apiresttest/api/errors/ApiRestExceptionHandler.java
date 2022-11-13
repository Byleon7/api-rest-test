package com.api.apiresttest.api.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.apiresttest.api.dtos.ResponseExceptionDto;
import com.api.apiresttest.api.util.ApiHelper;

@RestControllerAdvice
public class ApiRestExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(ApiRestExceptionHandler.class);

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ResponseExceptionDto> handleUnknownHostException(UnknownHostException e) {
        log.error("Exception unknown:", e.getMessage());

        ResponseExceptionDto response = new ResponseExceptionDto(ApiHelper.G100, ApiHelper.G100_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDto> handleException(Exception e) {
        log.error("Exception:", e.getMessage());

        ResponseExceptionDto response = new ResponseExceptionDto(ApiHelper.G100, ApiHelper.G100_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiRestException.class)
    public ResponseEntity<ResponseExceptionDto> handleApiException(ApiRestException e) {
        log.error("Api exception:", e.getMessage());

        ResponseExceptionDto response = new ResponseExceptionDto(e.getCode(), e.getMessage());
        return new ResponseEntity<>(response, ApiHelper.getHttpCode(e.getCode()));
    }
}
