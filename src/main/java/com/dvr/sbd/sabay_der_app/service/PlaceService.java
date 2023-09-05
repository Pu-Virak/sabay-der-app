package com.dvr.sbd.sabay_der_app.service;

import java.util.List;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.res.PlaceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.PlaceRes;

public interface PlaceService {

    public PlaceDetailRes retrievePlaceInfoByID(long id) throws SBDBaseException;

    public List<PlaceRes> retrieveListPlaceInfo(long page, long size) throws SBDBaseException;

}
