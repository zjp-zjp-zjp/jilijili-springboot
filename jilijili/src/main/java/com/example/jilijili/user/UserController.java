package com.example.jilijili.user;

import com.example.jilijili.test.Test;
import com.example.jilijili.video.Video;
import com.example.jilijili.video.VideoService;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView account_register_page(){
        return new ModelAndView("register");
    }
    @PostMapping(path = "register")
    public void account_register_post(@RequestParam String nickname,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      HttpServletResponse response){
        System.out.println(nickname);
        userService.addUser( new User(nickname,email,password));
        try {
            response.sendRedirect("/account/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //登录模块
    @GetMapping(path = "login")
    public ModelAndView account_login_page(){
        return new ModelAndView("login");
    }
    @PostMapping(path = "login")
    public void account_login_post(@RequestParam String nickname, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        System.out.println(nickname);
        System.out.println(password);
        if(userService.userLogin(nickname,password)){
            request.getSession().setAttribute("userId",userService.getIdByNickname(nickname));
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                String msg="password or nickname wrong";
                response.sendRedirect("/error/"+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //查看当前用户信息
    @GetMapping(path = "accountInfo")
    public User account_accountInfo_page( HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById((Long) request.getSession().getAttribute("userId"));
    }
    //查看当前用户vieolist
    @GetMapping(path = "videoList")
    public List<Video> account_videoList_page(HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId"));
    }
    //查看某个用户信息
    @GetMapping(path = "accountInfo/{hisId}")
    public User account_accountInfo_his_page(HttpServletRequest request,@PathVariable("hisId") Long hisId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById(hisId);
    }
//    查看某个用户videolist
    @GetMapping(path = "videoList/{hisId}")
    public List<Video> account_videoList_his_page(HttpServletRequest request,@PathVariable("hisId") Long hisId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId(hisId);
    }
    //删除用户的某个video
    @DeleteMapping(path = "videoList")
    public void account_videoList_delete(@RequestParam("videoId") Long videoId,HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before deleting");
        }
        videoService.deleteVideoWithId(videoId);
    }
    //登出
    @DeleteMapping(path = "accountInfo")
    public void account_accountInfo_delete(HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("you aren't login");
        }
        request.getSession().removeAttribute("userId");
    }
}