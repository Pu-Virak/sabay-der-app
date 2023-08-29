package com.dvr.sbd.sabay_der_app.service.impl;

import org.springframework.stereotype.Service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;
import com.dvr.sbd.sabay_der_app.repo.UserRepo;
import com.dvr.sbd.sabay_der_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserRes retrieveUserInfoByID(long id) throws SBDBaseException {
        return userRepo.retrieveUserInfoByID(id);
    }

}
