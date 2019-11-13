package com.lw.idgenetor.base.exception;

public class IdSysException extends RuntimeException {

    public IdSysException() {
        super();
    }

    public IdSysException(String message) {
        super(message);
    }

    public IdSysException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdSysException(Throwable cause) {
        super(cause);
    }
}
