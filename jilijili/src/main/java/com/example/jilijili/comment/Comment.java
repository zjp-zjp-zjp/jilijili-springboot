package com.example.jilijili.comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String content;
    @JsonFormat(pattern="yyyy-M-d")
    private LocalDate releaseTime;
    private Long supportNum;
    private Long targetVideo;
    private Long targetComment;
    @Transient
    private Optional<Comment> itComment;

    public Comment() {
    }

    public Comment(Long authorId, String content, LocalDate releaseTime, Long supportNum, Long targetVideo, Long targetComment) {
        this.authorId = authorId;
        this.content = content;
        this.releaseTime = releaseTime;
        this.supportNum = supportNum;
        this.targetVideo = targetVideo;
        this.targetComment = targetComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Long supportNum) {
        this.supportNum = supportNum;
    }

    public Long getTargetVideo() {
        return targetVideo;
    }

    public void setTargetVideo(Long targetVideo) {
        this.targetVideo = targetVideo;
    }

    public Long getTargetComment() {
        return targetComment;
    }

    public void setTargetComment(Long targetComment) {
        this.targetComment = targetComment;
    }

    public Optional<Comment> getItComment() {
        return itComment;
    }

    public void setItComment(Optional<Comment> itComment) {
        this.itComment = itComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", releaseTime=" + releaseTime +
                ", supportNum=" + supportNum +
                ", targetVideo=" + targetVideo +
                ", targetComment=" + targetComment +
                ", itComment=" + itComment +
                '}';
    }
}
