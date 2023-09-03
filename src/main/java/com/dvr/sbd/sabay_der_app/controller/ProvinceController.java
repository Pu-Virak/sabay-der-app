package com.dvr.sbd.sabay_der_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.sbd.sabay_der_app.exception.SBDBadRequestException;
import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.exception.SBDDuplicateException;
import com.dvr.sbd.sabay_der_app.exception.SBDNotFoundException;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.ProvinceReq;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceDetailRes;
import com.dvr.sbd.sabay_der_app.model.res.ProvinceRes;
import com.dvr.sbd.sabay_der_app.service.ProvinceService;
import com.dvr.sbd.sabay_der_app.service.UserService;
import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/province")
@RequiredArgsConstructor
@Slf4j
public class ProvinceController {

    private final ProvinceService provinceService;

    private final UserService userService;

    @GetMapping("/list-provinces")
    public ResponseEntity<?> retrieveListProvinceInfo(@RequestParam("page") long page, @RequestParam("size") long size)
            throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Retrieve List Provinces Start >>>>>>>>>>>>>>>>>");
        try {
            List<ProvinceRes> listProvinces = new ArrayList<>();
            listProvinces = this.provinceService.retrieveProvinceInfo(page, size);
            log.info(">>>>>>>>>>>> Data Response: {}", listProvinces);
            return new ResponseEntity<>(new BaseRes<>()
                    .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                            .setMessage("Retreive List Provinces Info Success!"))
                    .setPayload(listProvinces), HttpStatus.OK);
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retrieve List of Provinces Info Failed.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveProvinceInfoByID(@PathVariable(required = true) long id) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Retrieve Province Info by ID Start >>>>>>>>>>>>>>>>>");
        try {
            ProvinceDetailRes provinceInfo = new ProvinceDetailRes();
            provinceInfo = this.provinceService.retrieveProvinceInfoByID(id);
            log.info(">>>>>>>>>>>> Data Response: {}", provinceInfo);
            if (provinceInfo == null) {
                throw new SBDNotFoundException("Province ID = " + id + " was not found!");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Retreive Province Info by ID Success!"))
                        .setPayload(provinceInfo), HttpStatus.OK);
            }
        } catch (SBDNotFoundException nfe) {
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retreive Province Info by ID Failed.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProvinceInfoByID(@PathVariable(required = true) long id,
            @RequestBody ProvinceReq dataReq) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Update Province Info by ID Start >>>>>>>>>>>>>>>>>");
        try {
            ProvinceDetailRes provinceInfo = new ProvinceDetailRes();
            provinceInfo = this.provinceService.retrieveProvinceInfoByIDForUpdate(id);
            log.info(">>>>>>>>>>>> Data Response: {}", provinceInfo);
            if (provinceInfo == null) {
                log.error("Retrieve Province info by ID for update not found.");
                throw new SBDNotFoundException("Province ID = " + id + " was not found!");
            } else {
                boolean isSuccess = this.provinceService.updateProvinceInfoByID(id, dataReq);
                if (!isSuccess) {
                    throw new SBDBaseException("Update province Info by ID failed.");
                } else {
                    return new ResponseEntity<>(new BaseRes<>()
                            .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                    .setMessage("Update province Info by ID Success!"))
                            .setPayload(null), HttpStatus.OK);
                }
            }
        } catch (SBDNotFoundException nfe) {
            log.error("Update Province info by ID failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Update Province info by ID failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Update Province Info by ID Failed.");
        }
    }

    @PostMapping("/register-new")
    public ResponseEntity<?> registerProvinceInfo(@RequestBody ProvinceForRegisReq dataReq) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Register Province Info Start >>>>>>>>>>>>>>>>>");
        try {
            this.registerProvinceInfoValidation(dataReq);
            boolean isSuccess = this.provinceService.registerProvinceInfo(dataReq);
            if (!isSuccess) {
                throw new SBDBaseException("Update province Info by ID failed.");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.CREATED.value()).setSuccess(true)
                                .setMessage("Register province Info Success!"))
                        .setPayload(null), HttpStatus.CREATED);
            }
        } catch (SBDBadRequestException nfe) {
            log.error("Register Province info failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDDuplicateException nfe) {
            log.error("Register Province info failed.", nfe);
            throw new SBDDuplicateException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Register Province info failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Register Province info failed.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProvinceInfoByID(@PathVariable long id) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Delete Province Info Start >>>>>>>>>>>>>>>>>");
        try {
            // Validate param
            if (id == 0) {
                throw new SBDBadRequestException("Province ID for delete must not be null or empty!");
            }
            ProvinceDetailRes provinceData = this.provinceService.retrieveProvinceInfoByID(id);
            if (provinceData == null) {
                throw new SBDNotFoundException("Province ID = " + id + " was not found!");
            }
            boolean isSuccess = this.provinceService.deleteProvinceInfoByID(id);
            if (!isSuccess) {
                throw new SBDBaseException("Delete province Info by ID failed.");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Delete province Info Success!"))
                        .setPayload(null), HttpStatus.OK);
            }
        } catch (SBDNotFoundException nfe) {
            log.error("Delete Province info failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBadRequestException nfe) {
            log.error("Delete Province info failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Delete Province info failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Delete Province info failed.");
        }
    }

    private void registerProvinceInfoValidation(ProvinceForRegisReq dataForRegisReq) throws SBDBaseException {
        if (dataForRegisReq == null) {
            throw new SBDBadRequestException("Data for register must not be null.");
        }
        if (dataForRegisReq.getNameEn() == null || dataForRegisReq.getNameEn().isEmpty()) {
            throw new SBDBadRequestException("Name EN must not be null.");
        }
        if (dataForRegisReq.getNameKh() == null || dataForRegisReq.getNameKh().isEmpty()) {
            throw new SBDBadRequestException("Name KH must not be null.");
        }
        if (dataForRegisReq.getCreatedBy() == 0) {
            throw new SBDBadRequestException("Created by must not be null.");
        }
        if (dataForRegisReq.getUpdatedBy() == 0) {
            throw new SBDBadRequestException("Updated by must not be null.");
        }
        if (this.provinceService.retrieveProvinceInfoByUniqueNameEn(dataForRegisReq.getNameEn()) != null) {
            throw new SBDDuplicateException("Province name was already existed.");
        }
        if (this.provinceService.retrieveProvinceInfoByUniqueNameKh(dataForRegisReq.getNameKh()) != null) {
            throw new SBDDuplicateException("Province name was already existed.");
        }
        if (this.userService.retrieveUserInfoByID(dataForRegisReq.getCreatedBy()) == null) {
            throw new SBDDuplicateException("Created by was not existed.");
        }
        if (this.userService.retrieveUserInfoByID(dataForRegisReq.getUpdatedBy()) == null) {
            throw new SBDDuplicateException("Updated by was not existed.");
        }
    }

}
