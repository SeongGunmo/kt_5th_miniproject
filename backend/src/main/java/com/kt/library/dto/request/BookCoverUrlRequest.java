package com.kt.library.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCoverUrlRequest {
    // 어떤 책의 표지인지 알려주는 ID
    private Long bookId;

    // OpenAI에서 받은 이미지 URL
    private String coverImageUrl;
}


