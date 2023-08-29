package com.dvr.sbd.sabay_der_app.utils.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseStatusRes<T> {
    private String message;
    private int code;
    private boolean isSuccess;
}
