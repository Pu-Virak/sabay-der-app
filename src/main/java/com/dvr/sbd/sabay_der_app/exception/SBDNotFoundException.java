package com.dvr.sbd.sabay_der_app.exception;

public class SBDNotFoundException extends RuntimeException {
    public SBDNotFoundException(String message) {
        super(message);
    }

    public SBDNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
