package com.dvr.sbd.sabay_der_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.exception.SBDNotFoundException;
import com.dvr.sbd.sabay_der_app.model.res.PlaceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.PlaceRes;
import com.dvr.sbd.sabay_der_app.service.PlaceService;
import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> retrievePlaceInfoByID(@PathVariable(required = true) long id) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Retrieve Place Info by ID Start >>>>>>>>>>>>>>>>>");
        try {
            PlaceDetailRes placeDetailRes = new PlaceDetailRes();
            placeDetailRes = this.placeService.retrievePlaceInfoByID(id);
            log.info(">>>>>>>>>>>> Data Response: {}", placeDetailRes);
            if (placeDetailRes == null) {
                throw new SBDNotFoundException("Place ID = " + id + " was not found!");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Retreive Place Info by ID Success!"))
                        .setPayload(placeDetailRes), HttpStatus.OK);
            }
        } catch (SBDNotFoundException nfe) {
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retreive Place Info by ID Failed.");
        }
    }

    @GetMapping("/list-places")
    public ResponseEntity<?> retrieveListProvinceInfo(@RequestParam("page") long page, @RequestParam("size") long size)
            throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Retrieve List Place Start >>>>>>>>>>>>>>>>>");
        try {
            List<PlaceRes> listProvinces = new ArrayList<>();
            listProvinces = this.placeService.retrieveListPlaceInfo(page, size);
            log.info(">>>>>>>>>>>> Data Response: {}", listProvinces);
            return new ResponseEntity<>(new BaseRes<>()
                    .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value())
                            .setSuccess(true)
                            .setMessage("Retreive List Places Info Success!"))
                    .setPayload(listProvinces), HttpStatus.OK);
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retrieve List of Places Info Failed.");
        }
    }

}
