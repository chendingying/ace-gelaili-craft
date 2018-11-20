package com.ace.auth.server.service.impl;

import com.ace.api.vo.user.UserInfo;
import com.ace.auth.common.util.jwt.JWTInfo;
import com.ace.auth.server.feign.IUserService;
import com.ace.auth.server.service.AuthService;
import com.ace.auth.server.util.user.JwtAuthenticationRequest;
import com.ace.auth.server.util.user.JwtTokenUtil;
import com.ace.common.exception.auth.UserInvalidException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        // 获取验证码的代码
        if (session.getAttribute("imageCode") == null) {
            throw new UserInvalidException("重新获取验证码!");
        } else {
            if (session.getAttribute("imageCode").toString().equalsIgnoreCase(authenticationRequest.getCode())) {
                UserInfo info = userService.validate(authenticationRequest);
                if (!StringUtils.isEmpty(info.getId())) {
                    return jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
                }
            }else{
                throw new UserInvalidException("输入验证码错误!");
            }
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
