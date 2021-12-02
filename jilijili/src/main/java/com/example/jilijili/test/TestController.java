//package com.example.jilijili.test;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.LineNumberInputStream;
//import java.util.HashMap;
//import java.util.List;
//
//@Controller
//@RequestMapping("/test")
//public class TestController {
//    @GetMapping(path = "1")
//    public ModelAndView sayHello(HashMap<String, Object> map)    {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test1");//动态网页名称
//        modelAndView.addObject("list", List.of(1,2,3,4));
//        return modelAndView;
//    }
//
//    @PostMapping(path = "1")
//    public ModelAndView test1(HashMap<String, Object> map, @RequestParam String name, @RequestParam String msg)    {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test1");//动态网页名称
//        modelAndView.addObject("name",name );
//        modelAndView.addObject("msg",msg );
//        return modelAndView;
//    }
//
//}

//package com.example.jilijili.test;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class TestController {
//    @RequestMapping("/edit")
//    public String edit(Model model) {
//        String s = "小明";
//        model.addAttribute("stu", s);
//        return "form";
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.GET)
//    @ResponseBody
//    public String save(String id) {
//        System.out.println(id);
//        return id;    //代码简洁很多
//    }
//}

package com.example.jilijili.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String edit(Model model) {
        return "form";
    }

    @PostMapping(path="/A")
    public ModelAndView edit(@RequestBody Test t) {
        Test user = new Test();
        user.setId(t.getId());
        user.setName(t.getName());
        System.out.println(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test2");//动态网页名称
//        modelAndView.addObject("id", id);
        modelAndView.addObject("name", "msg");
        return modelAndView;
    }
    @GetMapping(path="1")
    public String edit1(Model model) {
        return "test1";
    }


}


