package com.example.jilijili.video;

import com.example.jilijili.comment.Comment;
import com.example.jilijili.comment.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class VideoService {
    @Resource
    private final VideoRepository videoRepository;
    @Resource
    private final CommentService commentService;

    public VideoService(VideoRepository videoRepository, CommentService commentService) {
        this.videoRepository = videoRepository;
        this.commentService = commentService;
    }

    public void uploadVideo(Video video){
        videoRepository.save(video);
    }
    public Optional<Video> getVideoById(Long id){
        Optional<Video> videoList=videoRepository.findById(id);
        if(!videoList.isPresent()){
            throw new IllegalStateException("no video with id "+id);
        }
        return videoList;
    }
    public Optional<Video> getVideoByAuthorId(Long authorId){
        Optional<Video> videoList=videoRepository.findVideoByAuthorId(authorId);
        if(!videoList.isPresent()){
            throw new IllegalStateException("no video uploaded by user with id "+authorId);
        }
        return videoList;
    }
    public Video_CommentReturn getVideoAndCommentById(Long videoId){
        Video_CommentReturn ret=new Video_CommentReturn();
        ret.setVideo(getVideoById(videoId));
        ret.setCommentList(commentService.getVideoComment(videoId));
        return ret;
    }
    public void addaComment(Comment comment){
        commentService.releaseComment(comment);
    }
}
