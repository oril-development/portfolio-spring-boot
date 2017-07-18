package com.orilinc.portfolio.test.runner;

import com.orilinc.portfolio.configuration.ApplicationRepositoryMockBeans;
import com.orilinc.portfolio.repositories.UserRepository;
import com.orilinc.portfolio.service.UserService;
import com.orilinc.portfolio.service.UserServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ApplicationServiceRunner  {
    @Configuration
    @Import({ApplicationRepositoryMockBeans.class})
    static class ContextConfig {

        @Autowired
        private UserRepository userRepository;

        @Bean
        static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        public UserService userService () {
            return new UserServiceImpl(userRepository);
        }
    }
}
