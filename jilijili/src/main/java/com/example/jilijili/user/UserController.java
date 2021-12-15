package com.example.jilijili.user;

import com.example.jilijili.video.VideoService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.annotation.Resource;
import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@RequestMapping(path = "account")
@RestController
public class UserController {
    @Resource
    private final UserService userService;
    @Resource
    private final VideoService videoService;

    public UserController(UserService userService, VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
    }

    //    注册模块
    @GetMapping(path = "register")
    public ModelAndView account_register_page() {
        return new ModelAndView("register");
    }

    @PostMapping(path = "register")
    public void account_register_post(@RequestParam String nickname,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      HttpServletResponse response) {
        System.out.println(nickname);
        userService.addUser(new User(nickname, email, password));
        try {
            response.sendRedirect("/account/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录模块
    @GetMapping(path = "login")
    public ModelAndView account_login_page() {
        return new ModelAndView("login");
    }

    @PostMapping(path = "login")
    public void account_login_post(@RequestParam String nickname, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(nickname);
        System.out.println(password);
        if (userService.userLogin(nickname, password)) {
            Long id = userService.getIdByNickname(nickname);
            request.getSession().setAttribute("userId", id);
            User user = userService.getUserById(id);
            request.getSession().setAttribute("userType", user.getType());
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String msg = "password or nickname wrong";
                response.sendRedirect("/error/" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getImageString(byte[] data) throws IOException {
        Base64.Encoder encoder=Base64.getEncoder();
        return data != null ? encoder.encodeToString(data) : "";
    }
    //查看当前用户信息
    @GetMapping(path = "accountInfo")
    public ModelAndView account_accountInfo_page(HttpServletRequest request) {
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before checking");
        }
        ModelAndView modelAndView=new ModelAndView("personalInfo");
        modelAndView.addObject("userInfo",userService.getUserById((Long)request.getSession().getAttribute("userId")));
        try {
            modelAndView.addObject("head",getImageString(userService.getUserById((Long)request.getSession().getAttribute("userId")).getHead()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("videoList",videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId")));
        modelAndView.addObject("listLength",videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId")).size());
        return modelAndView;
    }
    @PostMapping(path = "accountInfo")
    public void account_accountInfo_put(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam(value = "tel",required = false) String tel,
                                        @RequestParam(value = "email",required = false) String email,
                                        @RequestParam(value = "dob",required = false) String dob
                                        ){
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before checking");
        }
        Long id=(Long) request.getSession().getAttribute("userId");
        User user=userService.getUserById(id);
        if(tel!=null){
            System.out.println(tel);
            user.setTel(tel);
        }
        else System.out.println(0);
        if(email!=null){
            user.setEmail(email);
        }
        if(dob!=null){
            user.setDob(LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-M-d")));
        }
        userService.updateUser(user);
        try {
            response.sendRedirect("/account/accountInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping(value="logout")
    public void account_logout_delete(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("userId");
        try {
            response.sendRedirect("/account/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ModelAndView modelAndView=new ModelAndView("/account/login");
//        return modelAndView;
    }

//    //查看当前用户vieolist
//    @GetMapping(path = "videoList")
//    public List<Video> account_videoList_page(HttpServletRequest request) {
//        if (request.getSession().getAttribute("userId") == null) {
//            throw new IllegalStateException("please log in before updating");
//        }
//        return videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId"));
//    }
//
//    //查看某个用户信息
//    @GetMapping(path = "accountInfo/{hisId}")
//    public User account_accountInfo_his_page(HttpServletRequest request, @PathVariable("hisId") Long hisId) {
//        if (request.getSession().getAttribute("userId") == null) {
//            throw new IllegalStateException("please log in before updating");
//        }
//        return userService.getUserById(hisId);
//    }
//
//    //    查看某个用户videolist
//    @GetMapping(path = "videoList/{hisId}")
//    public List<Video> account_videoList_his_page(HttpServletRequest request, @PathVariable("hisId") Long hisId) {
//        if (request.getSession().getAttribute("userId") == null) {
//            throw new IllegalStateException("please log in before updating");
//        }
//        return videoService.getVideoByAuthorId(hisId);
//    }
//
//    //删除用户的某个video
//    @DeleteMapping(path = "videoList")
//    public void account_videoList_delete(@RequestParam("videoId") Long videoId, HttpServletRequest request) {
//        if (request.getSession().getAttribute("userId") == null) {
//            throw new IllegalStateException("please log in before deleting");
//        }
//        videoService.deleteVideoWithId(videoId);
//    }
//
//    //登出
//    @DeleteMapping(path = "accountInfo")
//    public void account_accountInfo_delete(HttpServletRequest request) {
//        if (request.getSession().getAttribute("userId") == null) {
//            throw new IllegalStateException("you aren't login");
//        }
//        request.getSession().removeAttribute("userId");
//    }
}
