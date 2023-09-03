package com.dvr.sbd.sabay_der_app.model.req;

import java.time.LocalDateTime;
import java.util.Date;

import com.dvr.sbd.sabay_der_app.constant.GenderConstant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReq {
    private String username;
    private String password;
    private GenderConstant gender;
    private Date dateOfBirth;
    private String profile;
    private String email;
    private String address;
    private boolean status;
    private LocalDateTime createdDateTime;
}
