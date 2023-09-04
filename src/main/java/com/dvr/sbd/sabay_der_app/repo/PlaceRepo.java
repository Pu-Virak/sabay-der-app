package com.dvr.sbd.sabay_der_app.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.dvr.sbd.sabay_der_app.constant.query.PlaceQueryScript;
import com.dvr.sbd.sabay_der_app.model.res.PlaceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;
import com.github.onozaty.mybatis.pg.type.list.StringListTypeHandler;

@Mapper
public interface PlaceRepo {

    @Result(property = "createdBy", column = "createdBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "updatedBy", column = "updatedBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "provinceData", column = "provinceID", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.ProvinceRepo.retrieveProvinceLessInfoByID"), javaType = UserRes.class)
    @Result(property = "slideShow", column = "slideShow", typeHandler = StringListTypeHandler.class)
    @Select(PlaceQueryScript.retrievePlaceInfoByID)
    public PlaceDetailRes retrievePlaceInfoByID(@Param("id") long id);

}
