package com.dvr.sbd.sabay_der_app.repo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dvr.sbd.sabay_der_app.constant.query.UserQueryScript;
import com.dvr.sbd.sabay_der_app.model.req.UserForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.UserReq;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;

@Mapper
public interface UserRepo {

    @Select(UserQueryScript.retrieveListUserInfo)
    public List<UserRes> retrieveListUserInfo(@Param("page") long page, @Param("size") long size);

    @Select(UserQueryScript.retrieveUserInfoByID)
    public UserRes retrieveUserInfoByID(@Param("id") long id);

    @Select(UserQueryScript.retrieveUserInfoByIDForUpdate)
    public UserRes retrieveUserInfoByIDForUpdate(@Param("id") long id);

    @Select(UserQueryScript.retrieveUserInfoByUsername)
    public UserRes retrieveUserInfoByUsername(@Param("username") String username);

    @Insert(UserQueryScript.registerUserInfo)
    public boolean registerUserInfo(@Param("userReq") UserForRegisReq userForRegisReq);

    @Update(UserQueryScript.updateUserInfoByID)
    public boolean updateUserInfo(@Param("id") long id, @Param("userReq") UserReq userReq);

    @Delete(UserQueryScript.deleteUserInfoByID)
    public boolean deleteUserInfoByID(@Param("id") long id);

}
