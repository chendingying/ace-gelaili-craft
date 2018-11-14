package com.ace.auth.client.exception;

/**
 * Created by cdy on 2018/11/14.
 */
public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
