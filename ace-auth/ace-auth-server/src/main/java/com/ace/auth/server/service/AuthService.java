package com.ace.auth.server.service;


import com.ace.auth.server.util.user.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest,HttpServletRequest request) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
}
