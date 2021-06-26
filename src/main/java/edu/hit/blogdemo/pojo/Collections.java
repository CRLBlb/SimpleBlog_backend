package edu.hit.blogdemo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Collections {
    private int collectionId;
    private Integer blogId;
    private Integer userId;
    private Timestamp collectionCreateTime;
    private Byte status;

    @Id
    @Column(name = "collection_id")
    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "collection_create_time")
    public Timestamp getCollectionCreateTime() {
        return collectionCreateTime;
    }

    public void setCollectionCreateTime(Timestamp collectionCreateTime) {
        this.collectionCreateTime = collectionCreateTime;
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
        Collections that = (Collections) o;
        return collectionId == that.collectionId && Objects.equals(blogId, that.blogId) && Objects.equals(userId, that.userId) && Objects.equals(collectionCreateTime, that.collectionCreateTime) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, blogId, userId, collectionCreateTime, status);
    }
}
