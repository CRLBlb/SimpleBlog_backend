package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.BlogDAO;
import edu.hit.blogdemo.dao.CollectionDAO;
import edu.hit.blogdemo.dao.LikesDAO;
import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Collections;
import edu.hit.blogdemo.pojo.Likes;
import edu.hit.blogdemo.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
//删除需要添加注解
@Transactional
public class BlogService {
    //注入DAO层
    @Autowired
    BlogDAO blogDAO;
    @Autowired
    LikesDAO likesDAO;
    @Autowired
    CollectionDAO collectionDAO;

    //分页查询所有博文信息(已通过审核文章)
    public MyPage list(int page, int size,int status) {
        MyPage<Blog> articles;
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        Page<Blog> articlesInDb = blogDAO.findAllByStatus(PageRequest.of(page, size, sort),status);
        articles = new MyPage<>(articlesInDb);
        return articles;
    }
    //分页查询点赞数大于某个数值的热门文章
    public MyPage findAllByLikeNumGreaterThanEqualAndStatus(int page, int size, int likesNum,int status){
        MyPage<Blog> articles;
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        Page<Blog> articlesInDb = blogDAO
                .findAllByLikeNumGreaterThanEqualAndStatus(PageRequest.of(page, size, sort),likesNum,status);
        articles = new MyPage<>(articlesInDb);
        return articles;
    }
    //分页根据标题模糊查询Blog所有信息(所有status=1的状态)
    public MyPage findAllByTitleLikeAndStatus(int page, int size,String  title,int status) {
        MyPage<Blog> articles;
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        Page<Blog> articlesInDb = blogDAO.findAllByTitleLikeAndStatus(PageRequest.of(page, size, sort)
                ,'%' + title + '%',1);
        articles = new MyPage<>(articlesInDb);
        return articles;
    }

    //根据Id寻找博文信息
    public Blog findByBlogId(int id) {
        Blog blog;
        blog = blogDAO.findByBlogId(id);
        return blog;
    }
    //查询所有博文信息
    public List<Blog> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "blogId");
        return blogDAO.findAll(sort);
    }
    //添加或更新博文信息
    public void addOrUpdate(Blog Blog) {
        blogDAO.save(Blog);
    }
    //根据Id删除博文信息
    public void deleteById(int id) {
        blogDAO.deleteById(id);
    }




    //消息模块查询所有博文
    public List<Blog> findAllByUserId(int userId){
        return blogDAO.findAllByUserId(userId);
    }

    public List<Blog> findAllLikeByUserId(int userId){
        List<Blog> rtn =  new ArrayList();
        List<Likes> likes = likesDAO.findAllByUserId(userId);
        for(Likes like:likes){
            rtn.add(blogDAO.findByBlogId(like.getBlogId()));
        }
        return rtn;
    }

    public List<Blog> findAllCollectionByUserId(int userId){
        List<Blog> rtn =  new ArrayList();
        List<Collections> collections = collectionDAO.findAllByUserId(userId);
        for(Collections col:collections){
            rtn.add(blogDAO.findByBlogId(col.getBlogId()));
        }
        return rtn;
    }


    public List<Blog> alllist(){
        Sort sort = Sort.by(Sort.Direction.DESC, "userId");
        return blogDAO.findAll(sort);
    }

    public List<Blog> userlist(int userid){
        return blogDAO.findByUserId(userid);
    }

    public void add(Blog blog){
        blogDAO.save(blog);
    }
    public void delete(int blogid){
        blogDAO.deleteByBlogId(blogid);
    }
    public Blog findById(int blogid){
        return blogDAO.findByBlogId(blogid);
    }

    //whn
    public Blog save(Blog blog) {
        return blogDAO.save(blog);
    }
    public List<Blog> findAll() {
        return blogDAO.findAll();
    }
}
