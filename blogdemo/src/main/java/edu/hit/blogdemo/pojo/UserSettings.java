package edu.hit.blogdemo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_settings", schema = "vueblog", catalog = "")
public class UserSettings {
    private int userId;
    private Byte isComment;
    private Byte isLike;
    private Byte isFollow;
    private Byte isBroadcast;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "is_comment")
    public Byte getIsComment() {
        return isComment;
    }

    public void setIsComment(Byte isComment) {
        this.isComment = isComment;
    }

    @Basic
    @Column(name = "is_like")
    public Byte getIsLike() {
        return isLike;
    }

    public void setIsLike(Byte isLike) {
        this.isLike = isLike;
    }

    @Basic
    @Column(name = "is_follow")
    public Byte getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Byte isFollow) {
        this.isFollow = isFollow;
    }

    @Basic
    @Column(name = "is_broadcast")
    public Byte getIsBroadcast() {
        return isBroadcast;
    }

    public void setIsBroadcast(Byte isBroadcast) {
        this.isBroadcast = isBroadcast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettings that = (UserSettings) o;
        return userId == that.userId && Objects.equals(isComment, that.isComment) && Objects.equals(isLike, that.isLike) && Objects.equals(isFollow, that.isFollow) && Objects.equals(isBroadcast, that.isBroadcast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, isComment, isLike, isFollow, isBroadcast);
    }
}
