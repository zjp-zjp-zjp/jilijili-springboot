package com.example.jilijili.entity;


import javax.persistence.*;

@Entity
@Table
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authorId;
    private String name;
    private String description;
    private String path;
    private String picturePath;
    private Long supportNum;
    private String subtitlePath;
    private String bulletPath;
    private String thumbnailPath;

    public Video() {
    }

    public Video(Long authorId, String name, String description, String path, String picturePath, Long supportNum, String subtitlePath, String bulletPath, String thumbnailPath) {
        this.authorId = authorId;
        this.name = name;
        this.description = description;
        this.path = path;
        this.picturePath = picturePath;
        this.supportNum = supportNum;
        this.subtitlePath = subtitlePath;
        this.bulletPath = bulletPath;
        this.thumbnailPath = thumbnailPath;
    }

    public String getSubtitlePath() {
        return subtitlePath;
    }

    public void setSubtitlePath(String subtitlePath) {
        this.subtitlePath = subtitlePath;
    }

    public String getBulletPath() {
        return bulletPath;
    }

    public void setBulletPath(String bulletPath) {
        this.bulletPath = bulletPath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }



    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Long supportNum) {
        this.supportNum = supportNum;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", supportNum=" + supportNum +
                '}';
    }
}
