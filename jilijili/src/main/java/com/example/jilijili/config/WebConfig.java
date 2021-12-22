package com.example.jilijili.config;


import com.example.jilijili.interception.LoginInterception;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterception())
                .addPathPatterns("/**")
                .excludePathPatterns("/account/login","/account/register");
    }
}
