package com.ace.auth.client.configuration;

import com.ace.auth.client.config.ServiceAuthConfig;
import com.ace.auth.client.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cdy on 2018/11/14.
 */
@Configuration
@ComponentScan({"com.ace.auth.client","com.ace.auth.common.event"})
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
