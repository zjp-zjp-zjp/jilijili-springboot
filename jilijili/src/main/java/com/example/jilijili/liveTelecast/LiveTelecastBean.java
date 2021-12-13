package com.example.jilijili.liveTelecast;

import javax.persistence.*;

@Entity
@Table
public class LiveTelecastBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roomId;
    private Long userId;

    public LiveTelecastBean() {
    }

    public LiveTelecastBean(Long roomId, Long userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public LiveTelecastBean(Long id, Long roomId, Long userId) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LiveTelecastBean{" +
                "roomId=" + roomId +
                ", userID=" + userId +
                '}';
    }
}
