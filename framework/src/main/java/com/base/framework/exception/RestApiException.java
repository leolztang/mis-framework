package com.base.framework.exception;

public class RestApiException extends RuntimeException {
    private static final long serialVersionUID = -4146842604448678017L;
    private final int code;

    public RestApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
