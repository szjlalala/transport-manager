package com.tms.common;

public class BizException extends RuntimeException {
    Results.ErrorCode errorCode;

    public BizException(Results.ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BizException(String message, Results.ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, Results.ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BizException(Throwable cause, Results.ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Results.ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public Results.ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Results.ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
