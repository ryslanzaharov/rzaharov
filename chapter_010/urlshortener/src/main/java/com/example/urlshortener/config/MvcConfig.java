package com.example.urlshortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registerAccount").setViewName("registerAccount");
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/statistic").setViewName("statistic");
    }
}
