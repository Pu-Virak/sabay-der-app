package com.dvr.sbd.sabay_der_app.exception;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String errMsg, Throwable throwable) {
        super(errMsg, throwable);
    }

    public FileStorageException(String errMsg) {
        super(errMsg);
    }

}
