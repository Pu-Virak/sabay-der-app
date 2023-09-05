package com.dvr.sbd.sabay_der_app.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/health-check")
@Slf4j
public class APIHealthCheckController {
    @GetMapping
    public ResponseEntity<?> testingController() throws SBDBaseException {
        try {
            LinkedHashMap<String, Object> apiData = new LinkedHashMap<>();
            String currentTime = LocalDateTime.now().toString();
            apiData.put("projectName", "Sabay Der Application");
            apiData.put("version", "1.0");
            apiData.put("author", "Det Virak");
            apiData.put("initDate", "2023-08-20");
            apiData.put("currentDateTime", currentTime);

            return new ResponseEntity<>(apiData, null, HttpStatus.OK);
        } catch (Exception e) {
            log.error("-> Error occurred.", e);
        }
        return null;
    }
}
