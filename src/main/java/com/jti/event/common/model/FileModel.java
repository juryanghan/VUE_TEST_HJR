package com.jti.event.common.model;

import lombok.Data;

@Data
public class FileModel {
    private String filename;
    private String oriFilename;
    private String htmlContent;
    private long fileSize;
    private String fileUrl;
}
