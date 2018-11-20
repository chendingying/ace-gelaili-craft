package com.ace.auth.server.service;


import com.ace.auth.server.util.user.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest,HttpSession session) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
}
