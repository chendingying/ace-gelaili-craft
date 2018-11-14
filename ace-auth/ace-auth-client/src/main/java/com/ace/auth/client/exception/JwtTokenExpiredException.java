package com.ace.auth.client.exception;

/**
 * Created by cdy on 2018/11/14.
 */
public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
