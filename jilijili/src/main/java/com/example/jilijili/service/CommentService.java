package com.example.jilijili.service;

import com.example.jilijili.repository.CommentRepository;
import com.example.jilijili.entity.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public void releaseComment(Comment comment){
        commentRepository.save(comment);
    }
    public List<Comment> getVideoComment(Long videoId){
        List<Comment> commentList= commentRepository.findAllByTargetVideo(videoId);
        if(commentList==null){
            return null;
        }
        for (Comment item:commentList) {
            item.setItComment(commentRepository.findAllByTargetComment(item.getId()));
        }
        return commentList;
    }
    public Comment getCommentById(Long commentId){
        if(!commentRepository.findById(commentId).isPresent()){
            throw new IllegalStateException("no comment with id "+commentId);
        }
        return commentRepository.findById(commentId).get();
    }
    public void removeCommentsOfVideo(Long videoId){
        List<Comment> commentList= commentRepository.findAllByTargetVideo(videoId);
        if(commentList!=null){
            for(Comment item:commentList){
                commentRepository.deleteCommentsByTargetComment(item.getId());
                commentRepository.deleteCommentById(item.getId());
            }
        }
    }
}
