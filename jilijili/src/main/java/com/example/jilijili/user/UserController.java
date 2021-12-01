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

    @PostMapping(path = "register")
    public void registerUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PostMapping(path = "login")
    public boolean userLogin(@RequestParam String nickname, @RequestParam String password, HttpServletRequest request){
        if(userService.userLogin(nickname,password)){
            request.getSession().setAttribute("userId",userService.getIdByNickname(nickname));
        }
        return userService.userLogin(nickname,password);
    }
    @GetMapping(path = "accountInfo")
    public User getUser( HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById((Long) request.getSession().getAttribute("userId"));
    }
    @GetMapping(path = "videoList")
    public List<Video> getThisUsersVideo(HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId"));
    }
    @GetMapping(path = "accountInfo/{hisId}")
    public User getHisUser(HttpServletRequest request,@PathVariable("hisId") Long hisId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return userService.getUserById(hisId);
    }
    @GetMapping(path = "videoList/{hisId}")
    public List<Video> getHisVideo(HttpServletRequest request,@PathVariable("hisId") Long hisId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId(hisId);
    }
}
