package com.ace.common.exception.auth;


import com.ace.common.constant.CommonConstants;
import com.ace.common.exception.BaseException;

/**
 * Created by cdy on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}

