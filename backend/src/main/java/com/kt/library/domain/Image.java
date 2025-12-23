package com.kt.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Image {
    // 기본키 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long id;

    // 이미지 주소
    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

}

