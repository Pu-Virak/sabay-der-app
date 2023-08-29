package com.dvr.sbd.sabay_der_app.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.sbd.sabay_der_app.exception.SBDNotFoundException;
import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/v1")
public class HomeController {
    @GetMapping
    public ResponseEntity<?> testingController() throws SBDNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode object = null;
        String data = """
                {
                    "name":
                        {
                            "first": "Tatu",
                            "last": "Saloranta"
                        },

                    "title": "Jackson founder",
                    "company": "FasterXML"
                }
                    """;
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("userID", "dvirak");
        dataMap.put("password", "Rakkkkkkkk");
        dataMap.put("address", "PP");
        dataMap.put("address", "PP");
        BaseRes<?> result = new BaseRes<>()
                .setStatus(new BaseStatusRes<>("Success!", HttpStatus.OK.value(), true))
                .setPayload(dataMap);
        // object = objectMapper.readTree(data);
        if (result != null) {
            throw new SBDNotFoundException("Not Found!");
        }

        return new ResponseEntity<>(result, new HttpHeaders(),
                HttpStatus.OK);
    }
}
