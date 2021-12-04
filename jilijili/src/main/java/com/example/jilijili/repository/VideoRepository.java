package com.example.jilijili.repository;

import com.example.jilijili.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {
    List<Video> findVideoByAuthorId(Long authorId);
    void deleteVideoById(Long videoId);
}
