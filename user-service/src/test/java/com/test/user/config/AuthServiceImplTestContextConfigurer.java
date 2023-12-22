package com.test.user.config;

import com.test.user.service.AuthService;
import com.test.user.serviceImpl.AuthServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthServiceImplTestContextConfigurer {
    @Bean
    public AuthService authService() {
        return new AuthServiceImpl();
    }
}
