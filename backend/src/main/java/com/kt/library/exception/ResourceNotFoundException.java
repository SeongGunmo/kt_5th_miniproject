package com.kt.library.exception;

// 찾는 책/이미지 없는 404 상황을 위한 전용 예외
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
