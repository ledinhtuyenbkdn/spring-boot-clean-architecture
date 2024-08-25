package com.example.springbootcleanarchitecture.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String messageCode;

    private final Object[] args;

    public BusinessException(String messageCode, Object ... args) {
        super((String)null);
        this.messageCode = messageCode;
        this.args = args;
    }
}
