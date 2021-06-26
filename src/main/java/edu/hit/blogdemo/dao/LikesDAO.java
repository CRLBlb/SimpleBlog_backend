package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Likes;
import edu.hit.blogdemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesDAO extends JpaRepository<Likes,Integer> {
    //保存更新Likes数据
    public Likes save(Likes likes);
    //通过blog_id和user_id进行查询
    public Likes findByBlogIdAndUserId(int blodId,int userId);
    //根据likeId删除某个点赞数据
    public void deleteByLikeId(int likeId);


    List<Likes> findAllByBlogId(int blogId);

    Likes findByLikeId(int likeId);
}
