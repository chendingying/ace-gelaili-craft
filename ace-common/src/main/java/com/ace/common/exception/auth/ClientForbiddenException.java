package com.ace.common.exception.auth;


import com.ace.common.constant.CommonConstants;
import com.ace.common.exception.BaseException;

/**
 * Created by cdy on 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
