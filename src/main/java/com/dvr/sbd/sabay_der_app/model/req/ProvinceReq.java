package com.dvr.sbd.sabay_der_app.model.req;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceReq {
    private String nameEn;
    private String nameKh;
    private String profile;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdDateTime;
}
