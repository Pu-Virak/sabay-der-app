package com.dvr.sbd.sabay_der_app.utils.validation;

import org.springframework.stereotype.Component;

import com.dvr.sbd.sabay_der_app.exception.SBDBadRequestException;
import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.exception.SBDDuplicateException;
import com.dvr.sbd.sabay_der_app.model.res.UserRes;
import com.dvr.sbd.sabay_der_app.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  User Validation Util
 * </pre>
 * 
 * @version 1.0, 2023-09-03
 * @author Det Virak
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidationUtil {

    private final UserService userService;

    private static final String ALPHANUMERIC_PATTERN = "[a-zA-Z0-9]+";

    /**
     * <pre>
     * Validate Password
     * 
     * <pre>
     * 
     * @return
     */
    public void isValidPassword(String pwd) throws SBDBaseException {
        try {
            if (pwd == null || pwd.trim().isEmpty()) {
                throw new SBDBadRequestException("Password must not be null or empty!");
            }
            if (pwd.trim().matches(ALPHANUMERIC_PATTERN)) {
                throw new SBDBadRequestException("Password must contains alphanumeric!");
            } else if (pwd.length() < 5) {
                throw new SBDBadRequestException("Password must longer than 5 digits!");
            }
        } catch (SBDBadRequestException nfe) {
            log.error("Validate Password failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Validate password failed.");
        }
    }

    /**
     * <pre>
     * Validate User ID
     * 
     * <pre>
     * 
     * @return
     */
    public void isValidUser(String userID) throws SBDBaseException {
        try {
            if (userID == null || userID.trim().isEmpty()) {
                throw new SBDBadRequestException("User ID for register must not be null or empty!");
            }
            UserRes userResData = this.userService.retrieveUserInfoByUsername(userID);
            if (userResData != null) {
                throw new SBDDuplicateException("User ID = " + userID + " was already existed!");
            }

            if (userID.trim().matches(ALPHANUMERIC_PATTERN)) {
                throw new SBDBadRequestException("User ID must contains alphanumeric!");
            } else if (userID.length() < 5) {
                throw new SBDBadRequestException("User ID must longer than 5 digits!");
            }

        } catch (SBDBadRequestException nfe) {
            log.error("Validate user ID failed.", nfe);
            throw new SBDBadRequestException(nfe.getMessage());
        } catch (SBDDuplicateException nfe) {
            log.error("Validate user ID failed.", nfe);
            throw new SBDDuplicateException(nfe.getMessage());
        } catch (SBDBaseException be) {
            log.error("Validate user ID failed.", be);
            throw new SBDBaseException(be.getMessage());
        } catch (Exception e) {
            log.error(">>>>>>>>>> Error occurred >>>>>>>>>>>>>", e);
            throw new SBDBaseException("Validate user ID failed.");
        }
    }
}
