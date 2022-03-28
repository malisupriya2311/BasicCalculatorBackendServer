package com.arithmatic.calculator.config;

import com.arithmatic.calculator.validator.RequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RequestValidator requestValidator(){
       return new RequestValidator();
    }
}
