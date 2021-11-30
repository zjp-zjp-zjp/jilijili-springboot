package com.example.jilijili.video;


import javax.persistence.*;

@Entity
@Table
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String name;
    private String description;
    private String path;
    private Long supportNum;

    public Video() {
    }

    public Video(String name, String description, String path, Long supportNum) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.supportNum = supportNum;
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
