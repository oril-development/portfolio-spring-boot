package com.orilinc.portfolio.configuration;

import com.orilinc.portfolio.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationServiceMockBeans {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }
}
