package com.dvr.sbd.sabay_der_app.utils.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class BaseRes<T> {

    private BaseStatusRes<T> status;
    private T payload;

}
