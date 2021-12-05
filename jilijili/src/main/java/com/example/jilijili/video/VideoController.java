package com.example.jilijili.video;

import com.example.jilijili.comment.Comment;
import com.example.jilijili.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Optional;
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
        if(request.getSession().getAttribute("userId")==null){
            throw new IllegalStateException("please log in before updating");
        }
        //判断文件是否为空
        if(file.isEmpty()&&picture.isEmpty()){
            model.addAttribute("message", "文件为空");
            return "uploadVideo";
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
            //判断文件父目录是否存在
            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }
            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            FileCopyUtils.copy(inputStream, outputStream);
            //返回上传成功的消息
            System.out.println("!!!!!!!!!!!!!!!!");//!!!!!!!
            System.out.println(path+fileName);//!!!!!!!
            System.out.println(name);//!!!!!!!
            System.out.println(description);//!!!!!!!
            model.addAttribute("message", "上传成功");
//            Video upVideo=new Video(name,description,, 0L);
//            upVideo.setAuthorId((Long) request.getSession().getAttribute("userId"));
//            videoService.uploadVideo(upVideo);
        } catch (IOException e) {
            e.printStackTrace();
            //返回上传失败的消息
            model.addAttribute("message", "上传失败");
        } finally {
            //无论成功与否，都关闭输入输出流
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
        //返回页面
        return "uploadVideo";
    }
    //查看视频
    @GetMapping(path = "{videoId}")
    public ModelAndView video_id_page(@PathVariable("videoId")Long videoId){
        System.out.println(videoService.getVideoAndCommentById(videoId));
        ModelAndView videoAndComments=new ModelAndView("videoAndComments");
        videoAndComments.addObject("data",videoService.getVideoAndCommentById(videoId));
        videoAndComments.addObject("comments",videoService.getVideoAndCommentById(videoId).getCommentList());
        return videoAndComments;
    }
    //评论
    @PostMapping(path = "{videoId}")
    @ResponseBody
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
