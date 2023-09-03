package com.dvr.sbd.sabay_der_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.model.req.UserForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.UserReq;
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

    @Override
    public boolean registerUserInfo(UserForRegisReq userForRegisReq) throws SBDBaseException {
        return userRepo.registerUserInfo(userForRegisReq);
    }

    @Override
    public UserRes retrieveUserInfoByUsername(String username) throws SBDBaseException {
        return userRepo.retrieveUserInfoByUsername(username);
    }

    @Override
    public List<UserRes> retrieveListUserInfo(long page, long size) throws SBDBaseException {
        return userRepo.retrieveListUserInfo(page, size);
    }

    @Override
    public UserRes retrieveUserInfoByIDForUpdate(long id) throws SBDBaseException {
        return userRepo.retrieveUserInfoByIDForUpdate(id);
    }

    @Override
    public boolean updateUserInfo(long id, UserReq userReq) throws SBDBaseException {
        return userRepo.updateUserInfo(id, userReq);
    }

    @Override
    public boolean deleteUserInfoByID(long id) throws SBDBaseException {
        return userRepo.deleteUserInfoByID(id);
    }

}
