package edu.hit.blogdemo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Follow {
    private int followId;
    private Integer fanId;
    private Integer starId;
    private Byte status;

    @Id
    @Column(name = "follow_id")
    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    @Basic
    @Column(name = "fan_id")
    public Integer getFanId() {
        return fanId;
    }

    public void setFanId(Integer fanId) {
        this.fanId = fanId;
    }

    @Basic
    @Column(name = "star_id")
    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return followId == follow.followId && Objects.equals(fanId, follow.fanId) && Objects.equals(starId, follow.starId) && Objects.equals(status, follow.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, fanId, starId, status);
    }
}
