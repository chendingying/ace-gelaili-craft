package com.ace.common.exception.process;

import com.ace.common.constant.CommonConstants;
import com.ace.common.exception.BaseException;

/**
 * Created by CDZ on 2018/11/17.
 */
public class ProcessInvalidException extends BaseException {
    public ProcessInvalidException(String message) {
        super(message, CommonConstants.EX_OTHER_CODE);
    }
}
