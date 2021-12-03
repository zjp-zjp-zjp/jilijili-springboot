package com.example.jilijili.video;

import com.example.jilijili.comment.Comment;
import com.example.jilijili.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "video")
public class VideoController {
    @Resource
    private final VideoService videoService;
    @Resource
    private final UserService userService;

    public VideoController(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }

    //上传视频
    @PostMapping(path = "uploadVideo")
    public void video_uploadVideo_post(@RequestBody Video video, HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        video.setAuthorId((Long) request.getSession().getAttribute("userId"));
        videoService.uploadVideo(video);
    }
    //查看视频
    @GetMapping(path = "{videoId}")
    public Video_CommentReturn video_id_page(@PathVariable("videoId")Long videoId){
        return videoService.getVideoAndCommentById(videoId);
    }
    //评论
    @PostMapping(path = "{videoId}")
    public void video_id_post(@RequestBody Comment comment, HttpServletRequest request, @PathVariable String videoId){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before commenting");
        }
        comment.setAuthorId((Long) request.getSession().getAttribute("userId"));
        comment.setAuthorNickname(userService.getUserById((Long) request.getSession().getAttribute("userId")).getNickname());
        videoService.addaComment(comment);
    }
    //点赞
    @PutMapping(path = "{videoId}")
    public void video_id_put(@RequestBody SupportContainer supportContainer,HttpServletRequest request){
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before supporting");
        }
        videoService.addSupport(supportContainer);
    }
}
