package com.kt.library.exception;

// 세션을 없거나 만료되었을 떄 던질 예외
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
