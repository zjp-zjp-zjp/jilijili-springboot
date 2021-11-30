package com.example.jilijili.video;

import com.example.jilijili.comment.Comment;
import java.util.List;
import java.util.Optional;

public class Video_CommentReturn {
    private Optional<Video> video;
    private List<Comment> commentList;

    public Video_CommentReturn() {

    }

    public Optional<Video> getVideo() {
        return video;
    }

    public void setVideo(Optional<Video> video) {
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
