package com.dvr.sbd.sabay_der_app.service;

import java.util.List;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.req.UserForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.UserReq;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;

public interface UserService {

    public List<UserRes> retrieveListUserInfo(long page, long size) throws SBDBaseException;

    public UserRes retrieveUserInfoByID(long id) throws SBDBaseException;

    public UserRes retrieveUserInfoByIDForUpdate(long id) throws SBDBaseException;

    public UserRes retrieveUserInfoByUsername(String username) throws SBDBaseException;

    public boolean registerUserInfo(UserForRegisReq userForRegisReq) throws SBDBaseException;

    public boolean updateUserInfo(long id, UserReq userReq) throws SBDBaseException;

    public boolean deleteUserInfoByID(long id) throws SBDBaseException;

}
