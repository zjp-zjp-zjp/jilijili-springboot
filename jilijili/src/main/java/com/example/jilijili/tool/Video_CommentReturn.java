package com.example.jilijili.tool;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.entity.Video;

import java.util.List;

public class Video_CommentReturn {
    private Video video;
    private List<Comment> commentList;

    public Video_CommentReturn() {

    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Video_CommentReturn{" +
                "video=" + video +
                ", commentList=" + commentList +
                '}';
    }
}
