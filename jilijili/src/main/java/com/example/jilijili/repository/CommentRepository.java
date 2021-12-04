package com.example.jilijili.repository;

import com.example.jilijili.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findAllByTargetVideo(Long videoId);
    List<Comment> findAllByTargetComment(Long commentId);
    void deleteCommentById(Long commentId);
    void deleteCommentsByTargetComment(Long commentId);
}
