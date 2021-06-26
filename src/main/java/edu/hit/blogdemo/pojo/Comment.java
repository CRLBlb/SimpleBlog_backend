package edu.hit.blogdemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private int commentId;
    private Integer userId;
    private Integer blogId;
    private String commentContent;
    //设置时间格式
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp commentCreateTime;
    private Byte status;

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
    @Column(name = "blog_id")
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "comment_content")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "comment_create_time")
    public Timestamp getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Timestamp commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
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
        Comment comment = (Comment) o;
        return commentId == comment.commentId && Objects.equals(userId, comment.userId) && Objects.equals(blogId, comment.blogId) && Objects.equals(commentContent, comment.commentContent) && Objects.equals(commentCreateTime, comment.commentCreateTime) && Objects.equals(status, comment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, blogId, commentContent, commentCreateTime, status);
    }
}
