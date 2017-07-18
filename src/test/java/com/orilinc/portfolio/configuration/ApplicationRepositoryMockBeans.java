package com.orilinc.portfolio.configuration;

import com.orilinc.portfolio.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationRepositoryMockBeans {

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}
