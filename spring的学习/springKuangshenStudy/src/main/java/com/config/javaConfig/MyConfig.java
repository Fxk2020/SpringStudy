package com.config.javaConfig;

import com.config.pogo.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public User2 getUser(){
        return new User2();
    }
}
