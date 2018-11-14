package com.ace.auth.server.configuration;

import com.ace.auth.server.interceptor.ClientTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cdy on 2018/11/14.
 */
@Configuration
public class FeignConfiguration {
    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }
}