package com.dvr.sbd.sabay_der_app.repo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dvr.sbd.sabay_der_app.constant.query.ProvinceQueryScript;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceReq;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceRes;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;

@Mapper
public interface ProvinceRepo {

    @Select(ProvinceQueryScript.retrieveListProvinceInfo)
    public List<ProvinceRes> retrieveProvinceInfo(@Param("page") long page, @Param("size") long size);

    @Result(property = "createdBy", column = "createdBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "updatedBy", column = "updatedBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Select(ProvinceQueryScript.retrieveProvinceInfoByID)
    public ProvinceDetailRes retrieveProvinceInfoByID(@Param("id") long id);

    @Result(property = "createdBy", column = "createdBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "updatedBy", column = "updatedBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Select(ProvinceQueryScript.retrieveProvinceInfoByUniqueNameEn)
    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameEn(@Param("nameEn") String nameEn);

    @Result(property = "createdBy", column = "createdBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "updatedBy", column = "updatedBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Select(ProvinceQueryScript.retrieveProvinceInfoByUniqueNameKh)
    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameKh(@Param("nameKh") String nameKh);

    @Result(property = "createdBy", column = "createdBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Result(property = "updatedBy", column = "updatedBy", one = @One(select = "com.dvr.sbd.sabay_der_app.repo.UserRepo.retrieveUserInfoByID"), javaType = UserRes.class)
    @Select(ProvinceQueryScript.retrieveProvinceInfoByIDForUpdate)
    public ProvinceDetailRes retrieveProvinceInfoByIDForUpdate(@Param("id") long id);

    @Update(ProvinceQueryScript.updateProvinceInfoByID)
    public boolean updateProvinceInfoByID(@Param("id") long id, @Param("provinceReq") ProvinceReq provinceReq);

    @Insert(ProvinceQueryScript.registerProvinceInfo)
    public boolean registerProvinceInfo(@Param("provinceReq") ProvinceForRegisReq provinceReq);
}
