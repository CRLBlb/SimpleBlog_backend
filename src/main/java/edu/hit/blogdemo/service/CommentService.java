package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.CommentDAO;
import edu.hit.blogdemo.pojo.Comment;
import edu.hit.blogdemo.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    //注入DAO层
    @Autowired
    CommentDAO commentDAO;

    //分页查询评论信息
    public MyPage list(int page, int size) {
        MyPage<Comment> comment;
        Sort sort = new Sort(Sort.Direction.DESC, "commentId");
        Page<Comment> commentInDb = commentDAO.findAll(PageRequest.of(page, size, sort));
        comment = new MyPage<>(commentInDb);
        return comment;
    }
    //通过commentId查找评论
    public Comment findByCommentId(int commentId) {
        Comment comment;
        comment = commentDAO.findByCommentId(commentId);
        return comment;
    }
    //查询所有评论信息
    public List<Comment> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "commentId");
        return commentDAO.findAll(sort);
    }
    //增加或更新评论信息
    public void addOrUpdate(Comment Comment) {
        commentDAO.save(Comment);
    }
    //通过commentId删除评论信息
    public void deleteById(int commentId) {
        commentDAO.deleteById(commentId);
    }

    public List<Comment>findAll(){
        List<Comment> all = commentDAO.findAll();
        return all;
    }
    public List<Comment>findAllByUserId(int userId){
        return commentDAO.findAllByUserId(userId);
    }
    public List<Comment>findAllByBlogId(int blogId){
        return commentDAO.findAllByBlogIdOrderByCommentCreateTime(blogId);
    }
    public void save(Comment comment){commentDAO.save(comment);}



}
