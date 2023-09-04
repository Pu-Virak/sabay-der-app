package com.dvr.sbd.sabay_der_app.service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.res.PlaceDetailRes;

public interface PlaceService {

    public PlaceDetailRes retrievePlaceInfoByID(long id) throws SBDBaseException;

}
