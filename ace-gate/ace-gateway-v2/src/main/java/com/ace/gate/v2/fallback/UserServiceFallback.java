package com.ace.gate.v2.fallback;

import com.ace.api.vo.authority.PermissionInfo;
import com.ace.gate.v2.feign.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ace
 * @create 2018/3/7.
 */
@Service
@Slf4j
public class UserServiceFallback implements IUserService {

    Logger log = LoggerFactory.getLogger(UserServiceFallback.class);
    @Override
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username) {
        log.error("调用{}异常{}","getPermissionByUsername",username);
        return null;
    }

    @Override
    public List<PermissionInfo> getAllPermissionInfo() {
        log.error("调用{}异常","getPermissionByUsername");
        return null;
    }
}
