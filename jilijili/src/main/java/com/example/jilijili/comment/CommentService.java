package com.example.jilijili.comment;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {
    @Resource
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
