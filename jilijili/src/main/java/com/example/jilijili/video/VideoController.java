package com.example.jilijili.video;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "video")
public class VideoController {
    @Resource
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @PostMapping(path = "uploadVideo")
    public void addVideo(@RequestBody Video video, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        video.setAuthorId((Long) request.getSession().getAttribute("userId"));
        videoService.uploadVideo(video);
    }
    @GetMapping(path = "/account/videoList")
    public Optional<Video> getThisUsersVideo(HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        return videoService.getVideoByAuthorId((Long) request.getSession().getAttribute("userId"));
    }
    @GetMapping(path = "{videoId}")
    public Optional<Video> getTheVideo(@PathVariable("videoId")Long videoId){
        return videoService.getVideoById(videoId);
    }
}
