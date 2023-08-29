package com.dvr.sbd.sabay_der_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceReq;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceRes;
import com.dvr.sbd.sabay_der_app.repo.ProvinceRepo;
import com.dvr.sbd.sabay_der_app.service.ProvinceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepo provinceRepo;

    @Override
    public List<ProvinceRes> retrieveProvinceInfo(long page, long size) throws SBDBaseException {
        return provinceRepo.retrieveProvinceInfo(page, size);
    }

    @Override
    public ProvinceDetailRes retrieveProvinceInfoByID(long id) throws SBDBaseException {
        return provinceRepo.retrieveProvinceInfoByID(id);
    }

    @Override
    public boolean updateProvinceInfoByID(long id, ProvinceReq provinceReq) throws SBDBaseException {
        return provinceRepo.updateProvinceInfoByID(id, provinceReq);
    }

    @Override
    public ProvinceDetailRes retrieveProvinceInfoByIDForUpdate(long id) throws SBDBaseException {
        return provinceRepo.retrieveProvinceInfoByIDForUpdate(id);
    }

    @Override
    public boolean registerProvinceInfo(ProvinceForRegisReq provinceReq) throws SBDBaseException {
        return provinceRepo.registerProvinceInfo(provinceReq);
    }

    @Override
    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameEn(String nameEn) {
        return provinceRepo.retrieveProvinceInfoByUniqueNameEn(nameEn);
    }

    @Override
    public ProvinceDetailRes retrieveProvinceInfoByUniqueNameKh(String nameKh) {
        return provinceRepo.retrieveProvinceInfoByUniqueNameKh(nameKh);
    }

}
