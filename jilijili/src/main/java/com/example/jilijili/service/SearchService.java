package com.example.jilijili.service;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.repository.CommentRepository;
import com.example.jilijili.entity.User;
import com.example.jilijili.repository.UserRepository;
import com.example.jilijili.entity.Video;
import com.example.jilijili.repository.VideoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchService {
    @Resource
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public SearchService(CommentRepository commentRepository,
                         UserRepository userRepository,
                         VideoRepository videoRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    public List<Comment> getComments(String search) {
        return commentRepository.findAllBySearch(search);
    }

    public List<User> getUsers(String search) {
        return userRepository.findAllBySearch(search);
    }

    public List<Video> getVideos(String search) {
        return videoRepository.findAllBySearch(search);
    }

}
