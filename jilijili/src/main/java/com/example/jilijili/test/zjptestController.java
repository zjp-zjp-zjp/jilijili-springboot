package com.example.jilijili.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "zjptest")
@RestController
public class zjptestController {
    @GetMapping
    public ModelAndView test1(){
        ModelAndView mav=new ModelAndView("zjptest");
        return mav;
    }
    @PostMapping
    public ModelAndView test2(@RequestBody Test test){
        ModelAndView mav=new ModelAndView("zjpshow");
        mav.addObject("jsonfile",test);
        return mav;
    }
//    @GetMapping(path = "testModelAndView")
//    public ModelAndView test3(){
//        ModelAndView mav=new ModelAndView("zjpshow");
//        return mav;
//    }
}
