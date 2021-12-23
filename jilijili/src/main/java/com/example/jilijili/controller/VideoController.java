package com.example.jilijili.controller;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.service.UserService;
import com.example.jilijili.tool.SupportContainer;
import com.example.jilijili.entity.Video;
import com.example.jilijili.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
@Controller
@RequestMapping(path = "/video")
public class VideoController {
    @Resource
    private final VideoService videoService;
    @Resource
    private final UserService userService;

    public VideoController(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }
    @GetMapping(path="upload")
    public String upload() {
        return "uploadVideo";
    }
    //上传视频
    @PostMapping(path = "upload")
    public String FileUpLoad(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("file") MultipartFile file,
            Model model,
            HttpServletRequest request) {
        System.out.println(name);
        System.out.println(description);
        System.out.println(videoService.getHowManyExists());
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        //判断文件是否为空
        if(file.isEmpty()&&picture.isEmpty()){
            model.addAttribute("message", "文件为空");
            return "uploadVideo";
        }
        //返回页面
        return "uploadVideo";
    }
    public static String getImageString(byte[] data) throws IOException {
        Base64.Encoder encoder=Base64.getEncoder();
        return data != null ? encoder.encodeToString(data) : "";
    }
    //查看视频
    @GetMapping(path = "{videoId}")
    public ModelAndView video_id_page(@PathVariable("videoId")Long videoId){
        ModelAndView videoAndComments=new ModelAndView("videoAndComments");
        Video video=videoService.getVideoAndCommentById(videoId).getVideo();
        byte[] avator=userService.getUserById(video.getAuthorId()).getHead();
        try {
            videoAndComments.addObject("avator",getImageString(avator));
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoAndComments.addObject("data",videoService.getVideoAndCommentById(videoId));
        videoAndComments.addObject("comments",videoService.getVideoAndCommentById(videoId).getCommentList());
        return videoAndComments;
    }
    //评论
    @PostMapping(path = "{videoId}")
    public void video_id_post(@RequestBody Comment comment, HttpServletRequest request, @PathVariable String videoId, HttpServletResponse response){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before commenting");
        }
        System.out.println(comment.getContent());
        comment.setAuthorId((Long) request.getSession().getAttribute("userId"));
        comment.setAuthorNickname(userService.getUserById((Long) request.getSession().getAttribute("userId")).getNickname());
        videoService.addaComment(comment);
        try {
            response.sendRedirect("/video/"+videoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //点赞
    @PutMapping(path = "{videoId}")
    public void video_id_put(@RequestBody SupportContainer supportContainer, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before supporting");
        }
        videoService.addSupport(supportContainer);
    }
}
