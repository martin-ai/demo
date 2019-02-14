package com.example.demo.Exception;

import java.text.MessageFormat;

public class ApiException extends RuntimeException {

    private String retcode;

    public ApiException(String retcode, String message, Throwable cause) {
        super(message, cause);
        this.retcode = retcode;
    }

    public ApiException(String retcode, String message) {
        super(message);
        this.retcode = retcode;
    }

    public ApiException(String message) {
        super(message);
    }

    public String getRetcode() {
        return retcode;
    }

    public static ApiException ofThrowable(Throwable e) {
        return new ApiException("ERROR", e.getMessage(), e);
    }

    public static ApiException ofMessage(String retcode, String pattern, Object... args) {
        return new ApiException(retcode, MessageFormat.format(pattern, args));
    }

}
