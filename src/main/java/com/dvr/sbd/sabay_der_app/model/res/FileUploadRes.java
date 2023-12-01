package com.dvr.sbd.sabay_der_app.model.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileUploadRes {
    private String fileName;
    private String fileDynamicURI;
    private String fileType;
    private long fileSize;
    // private String fileLocation;
    private byte[] fileData;
}
