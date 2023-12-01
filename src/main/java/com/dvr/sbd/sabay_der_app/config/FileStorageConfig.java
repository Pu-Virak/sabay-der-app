package com.dvr.sbd.sabay_der_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    @Value("${file.upload-dir}")
    private String uploadDir;
}
