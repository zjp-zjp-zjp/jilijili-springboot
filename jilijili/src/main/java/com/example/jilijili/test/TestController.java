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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
//    @GetMapping
//    public String edit(Model model) {
//        return "form";
//    }
//
//    @PostMapping(path="/A")
//    public ModelAndView edit(@RequestBody Test t) {
//        Test user = new Test();
//        user.setId(t.getId());
//        user.setName(t.getName());
//        System.out.println(user.getId());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test2");//动态网页名称
////        modelAndView.addObject("id", id);
//        modelAndView.addObject("name", "msg");
//        return modelAndView;
//    }
//    @GetMapping(path="1")
//    public String edit1(Model model) {
//        return "test1";
//    }


    //        return "about";

//        return "contact";

    //        return "genre";

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @GetMapping(path="index")
    public String test(Model model) {
        return "index";
    }

    @GetMapping(path="login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(path="register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping(path="lock")
    public String lock(Model model) {
        return "lock";
    }

    @GetMapping(path="404")
    public String error(Model model) {
        return "404";
    }

    @GetMapping(path="videoAndComments")
    public ModelAndView videoAndComments() {
        Test people1=new Test("0","blue",10,2,"good!");
        Test people2=new Test("1","start",10,2,"bad!");
        List<Test> datas = new ArrayList<Test>();
        datas.add(people1);
        datas.add(people2);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("videoAndComments");//动态网页名称
        //返回对象的属性：
        //video :id,src...
        modelAndView.addObject("datas", datas);
        return modelAndView;
        //评论的点赞数、披露内容、举报？、回复、时间、头像
    }

    @GetMapping(path="temp")
    public ModelAndView temp(Model model) {
        ModelAndView mmm =new ModelAndView("temp");
        mmm.addObject("data","hello");
        return mmm;
    }

}


