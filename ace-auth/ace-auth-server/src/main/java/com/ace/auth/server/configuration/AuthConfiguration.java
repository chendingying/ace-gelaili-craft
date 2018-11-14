package com.ace.auth.server.configuration;

import com.ace.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cdy on 2018/11/14.
 */
@Configuration
public class AuthConfiguration {
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
