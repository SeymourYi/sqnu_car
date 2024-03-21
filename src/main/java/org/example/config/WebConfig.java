package org.example.config;

import org.example.interception.LoginInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
     @Autowired
    private LoginInterception loginInterception;
     @Override
    public void addInterceptors(InterceptorRegistry registry){
         registry.addInterceptor(loginInterception).excludePathPatterns("/user/register","/user/login");
     }
}
