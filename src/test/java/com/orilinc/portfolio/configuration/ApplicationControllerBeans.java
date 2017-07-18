package com.orilinc.portfolio.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.orilinc.portfolio.controller")
@EnableWebMvc
public class ApplicationControllerBeans {
}
