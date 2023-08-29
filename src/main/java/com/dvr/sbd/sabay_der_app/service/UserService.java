package com.dvr.sbd.sabay_der_app.service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;

public interface UserService {
    public UserRes retrieveUserInfoByID(long id) throws SBDBaseException;
}
