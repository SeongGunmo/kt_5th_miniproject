package com.kt.library.exception;

// AI 이미지 생성 중 실패했을 때 던질 예외
public class ImageGenerationException extends RuntimeException {
    public ImageGenerationException(String message) {
        super(message);
    }
}
