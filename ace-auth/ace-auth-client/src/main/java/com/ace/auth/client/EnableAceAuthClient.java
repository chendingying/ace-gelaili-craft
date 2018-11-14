package com.ace.auth.client;

import com.ace.auth.client.configuration.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by cdy on 2018/11/14..
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableAceAuthClient {
}

