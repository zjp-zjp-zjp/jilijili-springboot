package com.example.jilijili.config;

import com.example.jilijili.tool.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//diy一些功能，只要写一些组件，然后交给springboot，它就会自动装配
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/account/login").setViewName("login");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/video/{id}").setViewName("videoAndComments");
    }

    //    注册国际化组件
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}

