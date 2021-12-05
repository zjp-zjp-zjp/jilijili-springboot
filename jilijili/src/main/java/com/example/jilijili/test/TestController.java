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

import com.example.jilijili.comment.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @GetMapping(path="form")
    public String form(Model model) {
        return "test1";
    }
    @PostMapping(path="form")
    @ResponseBody
    public Map<String,Object> getJsonVal(@RequestBody Map<String,Object> user) {
        System.out.println(" 1 " + user.get("content"));
        System.out.println(" 2 " + user.get("targetVideo"));
        return user;
    }

    @GetMapping(path="upload")
    public String upload() {
        return "uploadVideo";
    }

    @PostMapping(path="upload")
    public String SingleFileUpLoad(
            @RequestParam("name") String name,
            @RequestParam("myfile") MultipartFile file,
            Model model,
            HttpServletRequest request
            ) {
        model.addAttribute("name",name);
        System.out.println(name);
        //判断文件是否为空
        if(file.isEmpty()){
            model.addAttribute("result_singlefile", "文件为空");
            return "temp";
        }

        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //指定上传的基本位置
            String path = "e:/static/";
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //注意是路径+文件名
            File targetFile = new File(path + fileName);
            //如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"

            //判断文件父目录是否存在
            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }

            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);

            //告诉页面上传成功了
            model.addAttribute("result_singlefile", "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败
            model.addAttribute("result_singlefile", "上传失败");
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "temp";
    }
}


