package com.dvr.sbd.sabay_der_app.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceForRegisReq {
    private String nameEn;
    private String nameKh;
    private String profile;
    private long createdBy;
    private long updatedBy;
}
