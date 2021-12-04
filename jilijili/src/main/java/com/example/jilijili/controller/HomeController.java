package com.example.jilijili.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "home")
public class HomeController {
    @GetMapping
    public ModelAndView home_page(){
        return new ModelAndView("index");
    }
}
