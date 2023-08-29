package com.dvr.sbd.sabay_der_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;

/**
 * <pre>
 *  Base Controller Advice
 * </pre>
 * 
 * @author Det Virak
 * @version 1.0.0, 2023/082/6
 */
@RestControllerAdvice
public class BaseControllerAdvice {

        /**
         * <pre>
         *  Not Found Item Handle Message
         * </pre>
         * 
         * @param msg
         * @return
         */
        @ExceptionHandler(value = SBDNotFoundException.class)
        @ResponseStatus(value = HttpStatus.NOT_FOUND)
        public BaseRes<?> notFound(SBDNotFoundException msg) {
                return new BaseRes<>().setStatus(
                                new BaseStatusRes<>()
                                                .setCode(HttpStatus.NOT_FOUND.value())
                                                .setMessage(msg.getMessage())
                                                .setSuccess(false));
        }

        /**
         * <pre>
         *  Duplicate Item Handle Message
         * </pre>
         * 
         * @param msg
         * @return
         */
        @ExceptionHandler(value = SBDDuplicateException.class)
        @ResponseStatus(value = HttpStatus.CONFLICT)
        public BaseRes<?> duplicate(SBDDuplicateException msg) {
                return new BaseRes<>().setStatus(
                                new BaseStatusRes<>()
                                                .setCode(HttpStatus.CONFLICT.value())
                                                .setMessage(msg.getMessage())
                                                .setSuccess(false));
        }

        /**
         * <pre>
         *  General Error Handle Message
         * </pre>
         * 
         * @param msg
         * @return
         */
        @ExceptionHandler(value = SBDBaseException.class)
        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        public BaseRes<?> generalError(SBDBaseException msg) {
                return new BaseRes<>().setStatus(
                                new BaseStatusRes<>()
                                                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                .setMessage(msg.getMessage())
                                                .setSuccess(false));
        }

        /**
         * <pre>
         *  Bad Request Error Handle
         * </pre>
         * 
         * @param msg
         * @return
         */
        @ExceptionHandler(value = SDBBadRequestException.class)
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        public BaseRes<?> badRequest(SDBBadRequestException msg) {
                return new BaseRes<>().setStatus(
                                new BaseStatusRes<>()
                                                .setCode(HttpStatus.BAD_REQUEST.value())
                                                .setMessage(msg.getMessage())
                                                .setSuccess(false));
        }
}
