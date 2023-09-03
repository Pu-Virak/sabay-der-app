package com.dvr.sbd.sabay_der_app.service;

import java.util.List;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceReq;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceRes;

public interface ProvinceService {

    public List<ProvinceRes> retrieveProvinceInfo(long page, long size) throws SBDBaseException;

    public ProvinceDetailRes retrieveProvinceInfoByID(long id) throws SBDBaseException;

    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameEn(String nameEn);

    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameKh(String nameKh);

    public ProvinceDetailRes retrieveProvinceInfoByIDForUpdate(long id) throws SBDBaseException;

    public boolean updateProvinceInfoByID(long id, ProvinceReq provinceReq) throws SBDBaseException;

    public boolean registerProvinceInfo(ProvinceForRegisReq provinceReq) throws SBDBaseException;

    public boolean deleteProvinceInfoByID(long id) throws SBDBaseException;
}
