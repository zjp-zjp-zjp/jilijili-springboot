package com.example.jilijili.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authorId;
    private String authorNickname;
    private String content;
    @JsonFormat(pattern="yyyy-M-d HH:mm:ss")
    private LocalDateTime releaseTime;
    private Long supportNum;
    private Long targetVideo;
    private Long targetComment;
    @Transient
    private List<Comment> itComment;
    @Transient
    private String head64;


    public Comment() {
        releaseTime= LocalDateTime.now();
    }

    public Comment(String content,Long supportNum, Long targetVideo, Long targetComment) {
        this.content = content;
        this.supportNum = supportNum;
        this.targetVideo = targetVideo;
        this.targetComment = targetComment;
        releaseTime= LocalDateTime.now();
    }

    public String getHead64() {
        return head64;
    }

    public void setHead64(String head64) {
        this.head64 = head64;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
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

    public List<Comment> getItComment() {
        return itComment;
    }

    public void setItComment(List<Comment> itComment) {
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
