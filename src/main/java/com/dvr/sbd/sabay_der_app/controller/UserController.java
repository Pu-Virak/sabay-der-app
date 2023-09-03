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
import com.dvr.sbd.sabay_der_app.model.req.UserForRegisReq;
import com.dvr.sbd.sabay_der_app.model.req.UserReq;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;
import com.dvr.sbd.sabay_der_app.service.UserService;
import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;
import com.dvr.sbd.sabay_der_app.utils.secure.PasswordEncryptionUtil;
import com.dvr.sbd.sabay_der_app.utils.validation.UserValidationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserValidationUtil userValidationUtil;

    private final PasswordEncryptionUtil passwordEncryptionUtil;

    @PostMapping("/register-new")
    public ResponseEntity<?> registerUserInfo(@RequestBody UserForRegisReq dataReq) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Register user Info Start >>>>>>>>>>>>>>>>>");
        try {
            this.registerUserInfoValidation(dataReq);
            this.userValidationUtil.isValidUser(dataReq.getUsername());
            this.userValidationUtil.isValidPassword(dataReq.getPassword());
            String pwdEncrypted = passwordEncryptionUtil
                    .toHexString(passwordEncryptionUtil.getSHA512(dataReq.getPassword()));
            dataReq.setPassword(pwdEncrypted);
            boolean isSuccess = this.userService.registerUserInfo(dataReq);
            if (!isSuccess) {
                throw new SBDBaseException("Register user Info by ID failed.");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.CREATED.value()).setSuccess(true)
                                .setMessage("Register user Info Success!"))
                        .setPayload(null), HttpStatus.CREATED);
            }
        } catch (SBDBadRequestException nfe) {
            log.error("Register user info failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDDuplicateException nfe) {
            log.error("Register user info failed.", nfe);
            throw new SBDDuplicateException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Register user info failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Register user info failed.");
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveUserInfoByUsername(@RequestParam(name = "username") String userID)
            throws SBDBaseException {
        try {
            if (userID == null || userID.trim().isEmpty()) {
                throw new SBDBadRequestException("User name must not be null or empty!");
            }
            UserRes userResData = this.userService.retrieveUserInfoByUsername(userID);
            log.info(">>>>>>>>>>>> Data Response: {}", userResData);
            if (userResData == null) {
                throw new SBDNotFoundException("User name = " + userID + " was not found!");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Retreive user info by user name success!"))
                        .setPayload(userResData), HttpStatus.OK);
            }
        } catch (SBDBadRequestException nfe) {
            log.error("Retrieve user info by user name failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDDuplicateException nfe) {
            log.error("Retrieve user info by user name failed.", nfe);
            throw new SBDDuplicateException(nfe.getMessage());
        } catch (SBDNotFoundException nfe) {
            log.error("Retrieve user info by user name failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Retrieve user info by user name failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retrieve user info by user ID failed.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveUserInfoByID(@PathVariable long id)
            throws SBDBaseException {
        try {
            if (id == 0) {
                throw new SBDBadRequestException("User ID must not be null or empty!");
            }
            UserRes userResData = this.userService.retrieveUserInfoByID(id);
            log.info(">>>>>>>>>>>> Data Response: {}", userResData);
            if (userResData == null) {
                throw new SBDNotFoundException("User ID = " + id + " was not found!");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Retreive user info by ID success!"))
                        .setPayload(userResData), HttpStatus.OK);
            }
        } catch (SBDBadRequestException nfe) {
            log.error("Retrieve user info by user ID failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDDuplicateException nfe) {
            log.error("Retrieve user info by user ID failed.", nfe);
            throw new SBDDuplicateException(nfe.getMessage());
        } catch (SBDNotFoundException nfe) {
            log.error("Retrieve user info by user ID failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Retrieve user info by user ID failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retrieve user info by user ID failed.");
        }
    }

    @GetMapping("/list-users")
    public ResponseEntity<?> retrieveListuserInfo(@RequestParam("page") long page, @RequestParam("size") long size)
            throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Retrieve List User Start >>>>>>>>>>>>>>>>>");
        try {
            List<UserRes> listUser = new ArrayList<>();
            listUser = this.userService.retrieveListUserInfo(page, size);
            log.info(">>>>>>>>>>>> Data Response: {}", listUser);
            return new ResponseEntity<>(new BaseRes<>()
                    .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                            .setMessage("Retreive List User Info Success!"))
                    .setPayload(listUser), HttpStatus.OK);
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Retrieve List of User Info Failed.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserInfoByID(@PathVariable(required = true) long id,
            @RequestBody UserReq dataReq) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Update User Info by ID Start >>>>>>>>>>>>>>>>>");
        try {
            UserRes userResData = new UserRes();
            userResData = this.userService.retrieveUserInfoByIDForUpdate(id);
            log.info(">>>>>>>>>>>> Data Response: {}", userResData);
            if (userResData == null) {
                log.error("Retrieve user info by ID for update not found.");
                throw new SBDNotFoundException("User ID = " + id + " was not found!");
            } else {
                boolean isSuccess = this.userService.updateUserInfo(id, dataReq);
                if (!isSuccess) {
                    throw new SBDBaseException("Update user Info by ID failed.");
                } else {
                    return new ResponseEntity<>(new BaseRes<>()
                            .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                    .setMessage("Update user Info by ID Success!"))
                            .setPayload(null), HttpStatus.OK);
                }
            }
        } catch (SBDNotFoundException nfe) {
            log.error("Update user info by ID failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Update user info by ID failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Update user Info by ID Failed.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserInfoByID(@PathVariable long id) throws SBDBaseException {
        log.info(">>>>>>>>>>>>> Delete User Info Start >>>>>>>>>>>>>>>>>");
        try {
            // Validate param
            if (id == 0) {
                throw new SBDBadRequestException("User ID for delete must not be null or empty!");
            }
            UserRes userResData = this.userService.retrieveUserInfoByID(id);
            if (userResData == null) {
                throw new SBDNotFoundException("User ID = " + id + " was not found!");
            }
            boolean isSuccess = this.userService.deleteUserInfoByID(id);
            if (!isSuccess) {
                throw new SBDBaseException("Delete user info by ID failed.");
            } else {
                return new ResponseEntity<>(new BaseRes<>()
                        .setStatus(new BaseStatusRes<>().setCode(HttpStatus.OK.value()).setSuccess(true)
                                .setMessage("Delete user info Success!"))
                        .setPayload(null), HttpStatus.OK);
            }
        } catch (SBDNotFoundException nfe) {
            log.error("Delete user info failed.", nfe);
            throw new SBDNotFoundException(nfe.getMessage());
        } catch (SBDBadRequestException nfe) {
            log.error("Delete user info failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Delete user info failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Delete user info failed.");
        }
    }

    private void registerUserInfoValidation(UserForRegisReq userForRegisReq) throws SBDBaseException {
        if (userForRegisReq == null) {
            throw new SBDBadRequestException("User data for register must not be null or empty!");
        }
        if (userForRegisReq.getGender() == null) {
            throw new SBDBadRequestException("Gender must not be null or empty!");
        }
        if (userForRegisReq.getDateOfBirth() == null) {
            throw new SBDBadRequestException("Date of birth must not be null or empty!");
        }
    }

}
