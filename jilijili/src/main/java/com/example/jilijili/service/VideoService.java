package com.example.jilijili.service;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.tool.SupportContainer;
import com.example.jilijili.entity.Video;
import com.example.jilijili.repository.VideoRepository;
import com.example.jilijili.tool.Video_CommentReturn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
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
    public List<Video> getVideoByAuthorId(Long authorId){
        List<Video> videoList=videoRepository.findVideoByAuthorId(authorId);
        if(videoList==null){
            throw new IllegalStateException("no video uploaded by user with id "+authorId);
        }
        return videoList;
    }
    public Video_CommentReturn getVideoAndCommentById(Long videoId){
        Video_CommentReturn ret=new Video_CommentReturn();
        ret.setVideo(getVideoById(videoId).get());
        ret.setCommentList(commentService.getVideoComment(videoId));
        return ret;
    }
    public void addaComment(Comment comment){
        commentService.releaseComment(comment);
    }
    public void addSupport(SupportContainer supportContainer){
        if(supportContainer.getTypeOfSupport()==0){
            Optional<Video> temp=videoRepository.findById(supportContainer.getTargetId());
            if(!temp.isPresent()){
                throw new IllegalStateException("no video with id "+supportContainer.getTargetId());
            }
            else{
                Video video=temp.get();
                video.setSupportNum(video.getSupportNum()+1);
                videoRepository.save(video);
            }
        }
        else if(supportContainer.getTypeOfSupport()==1){
            Comment temp=commentService.getCommentById(supportContainer.getTargetId());
            temp.setSupportNum(temp.getSupportNum()+1);
            commentService.releaseComment(temp);
        }
        else throw new IllegalStateException("invalid request");
    }
    @Transactional
    public void deleteVideoWithId(Long videoId){
        commentService.removeCommentsOfVideo(videoId);
        videoRepository.deleteVideoById(videoId);
    }
    public Long getHowManyExists(){
        return videoRepository.count();
    }
}

