package com.example.autoapi.exception;

public class RequireException extends BaseException{
    public RequireException() {
    }

    public RequireException(String message) {
        super(message);
    }

    public RequireException(String message, Object... objects) {
        super(message, objects);
    }

    public RequireException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequireException(Throwable cause) {
        super(cause);
    }

    public RequireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
