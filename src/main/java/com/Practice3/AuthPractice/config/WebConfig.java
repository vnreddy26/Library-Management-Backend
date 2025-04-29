package com.Practice3.AuthPractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configuring static resource handling for images
        registry.addResourceHandler("/api/public/images/**")  // URL pattern
                .addResourceLocations("file:///C:/Users/vanga/Desktop/My Springboot/Library Management/images/"); // File path
    }
}

