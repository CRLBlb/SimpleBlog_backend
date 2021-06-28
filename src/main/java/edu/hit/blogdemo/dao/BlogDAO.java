package edu.hit.blogdemo.dao;


import edu.hit.blogdemo.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogDAO extends JpaRepository<Blog,Integer> {
    //通过标题进行模糊查询
    Page<Blog> findAllByTitleLike(Pageable var1,String title);
    //通过BlogId进行查询某个Blog
    Blog findByBlogId(int blogId);
    //通过BlogId进行查询所有Blog
    Blog findAllByBlogId(int blogId);

    //保存更新Blog数据
    public Blog save(Blog blog);

    public List<Blog> findAllByUserId(int userId);

    //ypy
    List<Blog> findByUserId(int userid);
    void deleteByBlogId(int blogid);
}
