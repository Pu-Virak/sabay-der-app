package com.dvr.sbd.sabay_der_app.repo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dvr.sbd.sabay_der_app.constant.query.FileQueryScript;
import com.dvr.sbd.sabay_der_app.model.res.FileUploadRes;

@Mapper
public interface FileUploadRepo {

    @Insert(FileQueryScript.registerFileUpload)
    public boolean registerFileUpload(@Param("f") FileUploadRes f);

    @Select(FileQueryScript.retrieveFileUploadByUniqueName)
    public FileUploadRes retrieveFileUploadByUniqueName(@Param("fileName") String fileName);

    @Select(FileQueryScript.retrieveFileUploadByID)
    public FileUploadRes retrieveFileUploadByID(@Param("id") long id);

}
