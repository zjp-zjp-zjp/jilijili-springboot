package com.example.jilijili.comment;

import com.example.jilijili.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Resource
    private final CommentRepository commentRepository;
    @Resource
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public void releaseComment(Comment comment){
        commentRepository.save(comment);
    }
    public static String getImageString(byte[] data) throws IOException {
        Base64.Encoder encoder=Base64.getEncoder();
        return data != null ? encoder.encodeToString(data) : "";
    }
    public List<Comment> getVideoComment(Long videoId){
        List<Comment> commentList= commentRepository.findAllByTargetVideo(videoId);
        if(commentList==null){
            return null;
        }
        for (Comment item:commentList) {
            try {
                item.setHead64(getImageString(userService.getUserById(item.getAuthorId()).getHead()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            item.setItComment(commentRepository.findAllByTargetComment(item.getId()));
            for(Comment subitem:item.getItComment()){
                try {
                    subitem.setHead64(getImageString(userService.getUserById((subitem.getAuthorId())).getHead()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
