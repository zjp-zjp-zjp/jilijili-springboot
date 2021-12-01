package com.example.jilijili.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findAllByTargetVideo(Long videoId);
    List<Comment> findAllByTargetComment(Long commentId);
}
