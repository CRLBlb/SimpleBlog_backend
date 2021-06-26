package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.BlogDAO;
import edu.hit.blogdemo.dao.LikesDAO;
import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Category;
import edu.hit.blogdemo.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service

public class BlogService {
    //注入DAO层
    @Autowired
    BlogDAO blogDAO;
    @Autowired
    LikesDAO likesDAO;
    @Autowired
    private CategoryService categoryService;


    public MyPage list(int page, int size) {
        MyPage<Blog> articles;
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        Page<Blog> articlesInDb = blogDAO.findAll(PageRequest.of(page, size, sort));
        articles = new MyPage<>(articlesInDb);
        return articles;
    }
    public Blog findByBlogId(int id) {
        Blog blog;
        blog = blogDAO.findByBlogId(id);
        return blog;
    }

    public List<Blog> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        return blogDAO.findAll(sort);
    }

    public void addOrUpdate(Blog Blog) {
        blogDAO.save(Blog);
    }

    public void deleteById(int id) {
        blogDAO.deleteById(id);
    }

    public List<Blog> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return blogDAO.findAllByCategory(category);
    }
    //模糊查询Blog
    public List<Blog> search(String keywords) {
        return blogDAO.findAllByTitleLike('%' + keywords + '%');
    }


}
