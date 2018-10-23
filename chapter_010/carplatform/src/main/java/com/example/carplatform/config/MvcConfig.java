package com.example.carplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/createCar").setViewName("CreateCar");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/createuser").setViewName("CreateUser");
        registry.addViewController("/editCar").setViewName("UpdateCar");
        registry.addViewController("/index").setViewName("index");
    }
}
