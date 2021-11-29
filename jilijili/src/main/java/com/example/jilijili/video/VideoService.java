package com.example.jilijili.video;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class VideoService {
    @Resource
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
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
}
