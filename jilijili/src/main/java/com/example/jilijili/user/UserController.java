package com.example.jilijili.user;

import com.example.jilijili.video.Video;
import com.example.jilijili.video.VideoService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @PostMapping(path = "register")
    public void registerUser(@RequestBody User user){
        userService.addUser(user);
    }
    //登录模块
    @PostMapping(path = "login")
    public boolean userLogin(@RequestParam String nickname, @RequestParam String password, HttpServletRequest request){
        if(userService.userLogin(nickname,password)){
            request.getSession().setAttribute("userId",userService.getIdByNickname(nickname));
        }
        return userService.userLogin(nickname,password);
    }
    //查看当前用户信息
    @GetMapping(path = "accountInfo")
    public User getUser( HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById((Long) request.getSession().getAttribute("userId"));
    }
    //查看当前用户vieolist
    @GetMapping(path = "videoList")
    public List<Video> getThisUsersVideo(HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId"));
    }
    //查看某个用户信息
    @GetMapping(path = "accountInfo/{hisId}")
    public User getHisUser(HttpServletRequest request,@PathVariable("hisId") Long hisId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById(hisId);
    }
//    查看某个用户videolist
    @GetMapping(path = "videoList/{hisId}")
    public List<Video> getHisVideo(HttpServletRequest request,@PathVariable("hisId") Long hisId){
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
    @PutMapping(path = "accountInfo")
    
}
