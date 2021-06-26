package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.LikesDAO;
import edu.hit.blogdemo.pojo.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//删除需要添加注解
@Transactional
public class LikesService {
    @Autowired
    LikesDAO likesDAO;


    //更新用户-点赞数据
    public void addOrUpdate(Likes likes) {
        likesDAO.save(likes);
    }
    //通过blogId和userId查询用户-点赞数据
    public Likes findByBlogIdAndUserId(int blogId, int userId){
        return likesDAO.findByBlogIdAndUserId(blogId,userId);
    }
    //查询所有用户-点赞数据信息
    public List<Likes> searchAllUserLikes(){
        return likesDAO.findAll();
    }
    //通过likeId删除某个用户-点赞数据信息
    public void deleteUserLikes(int likeId){
        likesDAO.deleteByLikeId(likeId);
    }
    public List<Likes>findAllByBlogId(int blogId){
        return likesDAO.findAllByBlogId(blogId);
    }
    public void save(Likes likes){likesDAO.save(likes);}
    public Likes findByLikeId(int id){
        return likesDAO.findByLikeId(id);
    }



}
