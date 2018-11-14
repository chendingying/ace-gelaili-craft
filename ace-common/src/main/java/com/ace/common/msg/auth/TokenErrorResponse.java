package com.ace.common.msg.auth;

import com.ace.common.constant.RestCodeConstants;
import com.ace.common.msg.BaseResponse;

/**
 * Created by cdy on 2017/8/23.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
