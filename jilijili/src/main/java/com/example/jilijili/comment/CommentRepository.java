package com.example.jilijili.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTargetVideo(Long videoId);

    List<Comment> findAllByTargetComment(Long commentId);

    void deleteCommentById(Long commentId);

    void deleteCommentsByTargetComment(Long commentId);

    @Query(value = "select * from comment where(content LIKE concat('%',?1,'%'))", nativeQuery = true)
    List<Comment> findAllBySearch(String search);


}
