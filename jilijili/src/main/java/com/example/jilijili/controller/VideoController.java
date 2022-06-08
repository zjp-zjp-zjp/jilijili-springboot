package com.example.jilijili.controller;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.service.UserService;
import com.example.jilijili.tool.SupportContainer;
import com.example.jilijili.entity.Video;
import com.example.jilijili.service.VideoService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.FileNameMap;
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

    @GetMapping(path = "upload")
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
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before updating");
        }
        //判断文件是否为空
        if (file.isEmpty() && picture.isEmpty()) {
            model.addAttribute("message", "文件为空");
            return "uploadVideo";
        }
        Long temp = videoService.getHowManyExists();
        temp = temp + 1;
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDJSN3SaT5PTGm1zf6HBoBJAk47tObYEdk", "sTosgkY5LL3UeZDZeT1JJ2VieKktlLfi");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient((com.qcloud.cos.auth.COSCredentials) cred, clientConfig);
        // bucket名需包含appid
        String bucketName = "jilijili-1308757732";
        //获取上传时的文件名
        String FileName1 = file.getOriginalFilename();
        String FileName2 = picture.getOriginalFilename();
        String count=""+temp;
        String key = "/video/" +count+"/"+ FileName1;//video
        //写一个文件格式改变的函数
        //把MultipartFile转化为File
        File fileVideo = new File(file.getOriginalFilename());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), fileVideo);
        } catch (IOException e) {
            model.addAttribute("message", "文件转化失败");
        }
        //end
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, fileVideo);
        // 限流使用的单位是bit/s, 这里测试1MB/s的上传带宽限制
        putObjectRequest.setTrafficLimit(8 * 1024 * 1024);
        // 设置存储类型, 默认是标准(Standard)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            String crc64 = putObjectResult.getCrc64Ecma();
        } catch (CosClientException e) {
            model.addAttribute("message", "文件上传失败，请重新上传");
            return "uploadVideo";
        }
        String key1 = "/video/"  +count+"/"+ FileName2;//picture
        //把MultipartFile转化为File
        File filePicture = new File(picture.getOriginalFilename());
        try {
            FileUtils.copyInputStreamToFile(picture.getInputStream(), filePicture);
        } catch (IOException e) {
            model.addAttribute("message", "文件转化失败");
        }
        //end
        PutObjectRequest putObjectRequest1 = new PutObjectRequest(bucketName, key1, filePicture);
        // 限流使用的单位是bit/s, 这里测试1MB/s的上传带宽限制
        putObjectRequest1.setTrafficLimit(8 * 1024 * 1024);
        // 设置存储类型, 默认是标准(Standard)
        putObjectRequest1.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest1);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            String crc64 = putObjectResult.getCrc64Ecma();
        } catch (CosClientException e) {
            model.addAttribute("message", "文件上传失败，请重新上传");
            return "uploadVideo";
        }
        // 关闭客户端
        cosclient.shutdown();
        //返回页面
        model.addAttribute("message", "上传文件成功");
        videoService.uploadVideo(new Video((Long) request.getSession().getAttribute("userId"), name, description, key, key1, 0l, key, key, key));
        //删除临时文件
        if (fileVideo.exists()) {
            fileVideo.delete();
        }
        if (filePicture.exists()) {
            filePicture.delete();
        }
        return "uploadVideo";
    }

    public static String getImageString(byte[] data) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        return data != null ? encoder.encodeToString(data) : "";
    }

    //查看视频
    @GetMapping(path = "{videoId}")
    public ModelAndView video_id_page(@PathVariable("videoId") Long videoId) {
        ModelAndView videoAndComments = new ModelAndView("videoAndComments");
        Video video = videoService.getVideoAndCommentById(videoId).getVideo();
        byte[] avator = userService.getUserById(video.getAuthorId()).getHead();
        try {
            videoAndComments.addObject("avator", getImageString(avator));
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoAndComments.addObject("data", videoService.getVideoAndCommentById(videoId));
        videoAndComments.addObject("comments", videoService.getVideoAndCommentById(videoId).getCommentList());
        return videoAndComments;
    }

    //评论
    @PostMapping(path = "{videoId}")
    public void video_id_post(@RequestBody Comment comment, HttpServletRequest request, @PathVariable String videoId, HttpServletResponse response) {
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before commenting");
        }
        System.out.println(comment.getContent());
        System.out.println("发布评论");
        comment.setAuthorId((Long) request.getSession().getAttribute("userId"));
        comment.setAuthorNickname(userService.getUserById((Long) request.getSession().getAttribute("userId")).getNickname());
        videoService.addaComment(comment);
        try {
            response.sendRedirect("/video/" + videoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //点赞
    @PutMapping(path = "{videoId}")
    public void video_id_put(@RequestBody SupportContainer supportContainer, HttpServletRequest request) {
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before supporting");
        }
        videoService.addSupport(supportContainer);
    }
}
