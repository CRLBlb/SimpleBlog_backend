package edu.hit.blogdemo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_broadcast", schema = "vueblog", catalog = "")
public class UserBroadcast {
    private int id;
    private Integer userId;
    private Integer broadcastId;
    private byte status;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "broadcast_id")
    public Integer getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Integer broadcastId) {
        this.broadcastId = broadcastId;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBroadcast that = (UserBroadcast) o;
        return id == that.id && status == that.status && Objects.equals(userId, that.userId) && Objects.equals(broadcastId, that.broadcastId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, broadcastId, status);
    }
}
