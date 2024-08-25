package com.example.springbootcleanarchitecture.common.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AdapterException.class, BadRequestException.class, BusinessException.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, NativeWebRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof AdapterException subEx) {
            return this.handleAdapterException(subEx, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        } else if (ex instanceof BadRequestException subEx) {
            return this.handleBadRequestException(subEx, headers, HttpStatus.BAD_REQUEST, request);
        } else if (ex instanceof BusinessException subEx) {
            return this.handleBusinessException(subEx, headers, HttpStatus.CONFLICT, request);
        } else {
            throw ex;
        }
    }

    private ResponseEntity<Object> handleAdapterException(AdapterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail body = this.createProblemDetail(ex, status, ex.getMessage(), null, null, request);
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail body = this.createProblemDetail(ex, status, ex.getMessage(), null, null, request);
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleBusinessException(BusinessException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String defaultDetail = "Business logic constraint violated: " + ex.getMessageCode();
        ProblemDetail body = this.createProblemDetail(ex, status, defaultDetail, ex.getMessageCode(), ex.getArgs(), request);
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }
}
