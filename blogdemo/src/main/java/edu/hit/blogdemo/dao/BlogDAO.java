package edu.hit.blogdemo.dao;


import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogDAO extends JpaRepository<Blog,Integer> {
//    List<Blog> findAllByCategory(Category category);
    //通过标题进行模糊查询
    List<Blog> findAllByTitleLike(String keyword1);
    //通过类别进行查询
    List<Blog> findAllByCategory(Category category);
    //通过BlogId进行查询某个Blog
    Blog findByBlogId(int blogId);
    //通过BlogId进行查询所有Blog
    Blog findAllByBlogId(int blogId);

    //保存更新Blog数据
    public Blog save(Blog blog);
}
