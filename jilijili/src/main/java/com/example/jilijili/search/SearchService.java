package com.example.jilijili.search;

import com.example.jilijili.comment.Comment;
import com.example.jilijili.comment.CommentRepository;
import com.example.jilijili.user.User;
import com.example.jilijili.user.UserRepository;
import com.example.jilijili.video.Video;
import com.example.jilijili.video.VideoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
