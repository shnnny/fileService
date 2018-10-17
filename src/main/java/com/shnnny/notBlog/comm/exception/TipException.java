package com.shnnny.notBlog.comm.exception;

/**
 * Created by zzh on 2018/9/21.
 */
public class TipException extends RuntimeException{

    private static final long serialVersionUID = -5410092408293417486L;

    public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }
}
