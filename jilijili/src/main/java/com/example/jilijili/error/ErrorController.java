package com.example.jilijili.error;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "error/{message}")
public class ErrorController {
    @GetMapping
    public ModelAndView error(@PathVariable String message){
        ModelAndView err=new ModelAndView();
        err.setViewName("error");
        err.addObject("message",message);
        return  err;
    }
}
