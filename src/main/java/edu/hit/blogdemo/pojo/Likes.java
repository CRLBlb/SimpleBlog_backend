package edu.hit.blogdemo.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Likes {
    private int likeId;
    private int blogId;
    private int userId;
    private Timestamp likeCreateTime;
    private Byte status;

    public Likes(int likeId, int blogId, int userId, Timestamp likeCreateTime, Byte status) {
        this.likeId = likeId;
        this.blogId = blogId;
        this.userId = userId;
        this.likeCreateTime = likeCreateTime;
        this.status = status;
    }

    public Likes() {

    }

    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    @Basic
    @Column(name = "blog_id")
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "like_create_time")
    public Timestamp getLikeCreateTime() {
        return likeCreateTime;
    }

    public void setLikeCreateTime(Timestamp likeCreateTime) {
        this.likeCreateTime = likeCreateTime;
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
        Likes likes = (Likes) o;
        return likeId == likes.likeId && Objects.equals(blogId, likes.blogId) && Objects.equals(userId, likes.userId) && Objects.equals(likeCreateTime, likes.likeCreateTime) && Objects.equals(status, likes.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likeId, blogId, userId, likeCreateTime, status);
    }
}
