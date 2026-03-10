package com.cooperative.voting.domain.exception;

public class CpfValidationException extends RuntimeException {

    public CpfValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
