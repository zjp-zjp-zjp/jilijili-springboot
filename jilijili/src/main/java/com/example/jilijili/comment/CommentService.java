package com.example.jilijili.comment;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
            throw new IllegalStateException("no comment for this video");
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
}
