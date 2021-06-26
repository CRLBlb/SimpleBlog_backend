package edu.hit.blogdemo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Broadcast {
    private int broadcastId;
    private Integer adminId;
    private Integer userId;
    private String broadcastContent;
    private Timestamp broadcastCreateTime;

    @Id
    @Column(name = "broadcast_id")
    public int getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(int broadcastId) {
        this.broadcastId = broadcastId;
    }

    @Basic
    @Column(name = "admin_id")
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "broadcast_content")
    public String getBroadcastContent() {
        return broadcastContent;
    }

    public void setBroadcastContent(String broadcastContent) {
        this.broadcastContent = broadcastContent;
    }

    @Basic
    @Column(name = "broadcast_create_time")
    public Timestamp getBroadcastCreateTime() {
        return broadcastCreateTime;
    }

    public void setBroadcastCreateTime(Timestamp broadcastCreateTime) {
        this.broadcastCreateTime = broadcastCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Broadcast broadcast = (Broadcast) o;
        return broadcastId == broadcast.broadcastId && Objects.equals(adminId, broadcast.adminId) && Objects.equals(userId, broadcast.userId) && Objects.equals(broadcastContent, broadcast.broadcastContent) && Objects.equals(broadcastCreateTime, broadcast.broadcastCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(broadcastId, adminId, userId, broadcastContent, broadcastCreateTime);
    }
}
