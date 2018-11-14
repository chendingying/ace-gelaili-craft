package com.ace.auth.server.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cdy on 2018/11/14.
 */
@Configuration
@Data
public class UserConfiguration {
    @Value("${jwt.token-header}")
    private String userTokenHeader;

    public String getUserTokenHeader() {
        return userTokenHeader;
    }

    public void setUserTokenHeader(String userTokenHeader) {
        this.userTokenHeader = userTokenHeader;
    }
}
