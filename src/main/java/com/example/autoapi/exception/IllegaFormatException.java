package com.example.autoapi.exception;

public class IllegaFormatException extends BaseException{
    public IllegaFormatException() {
    }

    public IllegaFormatException(String message) {
        super(message);
    }

    public IllegaFormatException(String message, Object... objects) {
        super(message, objects);
    }

    public IllegaFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegaFormatException(Throwable cause) {
        super(cause);
    }

    public IllegaFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
