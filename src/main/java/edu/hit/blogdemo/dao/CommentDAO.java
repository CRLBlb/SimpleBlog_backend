package edu.hit.blogdemo.dao;



import edu.hit.blogdemo.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment,Integer> {
    //通过CommentId进行查询某个Comment
    Comment findByCommentId(int commentId);
    //通过CommentId进行查询所有Comment
    Comment findAllByCommentId(int commentId);
    //保存更新Comment数据
    public Comment save(Comment comment);

    //消息模块方法
    List<Comment> findAll();
    List<Comment> findAllByUserId(int userId);
    List<Comment> findAllByBlogIdOrderByCommentCreateTime(int blogId);
}
