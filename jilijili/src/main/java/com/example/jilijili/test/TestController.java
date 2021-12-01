package com.example.jilijili.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
@Controller
public class TestController {
    @RequestMapping("/test")
    public ModelAndView sayHello(HashMap<String, Object> map)    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");//动态网页名称
        modelAndView.addObject("key", 12345);
        return modelAndView;
    }

}
