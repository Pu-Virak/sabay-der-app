package com.dvr.sbd.sabay_der_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.res.PlaceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.PlaceRes;
import com.dvr.sbd.sabay_der_app.repo.PlaceRepo;
import com.dvr.sbd.sabay_der_app.service.PlaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepo placeRepo;

    @Override
    public PlaceDetailRes retrievePlaceInfoByID(long id) throws SBDBaseException {
        return placeRepo.retrievePlaceInfoByID(id);
    }

    @Override
    public List<PlaceRes> retrieveListPlaceInfo(long page, long size) throws SBDBaseException {
        return placeRepo.retrieveListPlaceInfo(page, size);
    }

}
