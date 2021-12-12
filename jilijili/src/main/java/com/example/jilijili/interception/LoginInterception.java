package com.example.jilijili.interception;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterception implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(request.getSession().getAttribute("userId") == null){
//            response.sendRedirect("/account/login");
//            return false;
//        }
        return true;
    }
}
