package com.dvr.sbd.sabay_der_app.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.dvr.sbd.sabay_der_app.constant.query.UserQueryScript;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;

@Mapper
public interface UserRepo {

    @Select(UserQueryScript.retrieveUserInfoByID)
    public UserRes retrieveUserInfoByID(long id);

}
