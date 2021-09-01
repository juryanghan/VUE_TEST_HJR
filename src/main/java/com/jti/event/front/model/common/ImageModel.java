package com.jti.event.front.model.common;

import lombok.Data;

@Data
public class ImageModel {
    private String originName;					// 이미지 원본명
    private String imagePath;					// 이미지 저장 경로
    private String imageUrl;					// 이미지 Url
    private long imageSize;					// 이미지 사이트
    private int imageWidth;					// 이미지 넓이
    private int imageHeight;					// 이미지 높이
}
