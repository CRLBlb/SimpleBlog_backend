package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Comment;
import edu.hit.blogdemo.pojo.Likes;
import edu.hit.blogdemo.service.BlogService;
import edu.hit.blogdemo.service.LikesService;
import edu.hit.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LikesController {
    @Autowired
    BlogService blogService;
    @Autowired
    LikesService likesService;
    @Autowired
    UserService userService;
    @CrossOrigin
    @GetMapping(value = "/api/get_like/{id}/{page}/{size}")
    @ResponseBody
    public List<Map<String,Object>> get_comment(@PathVariable int id,@PathVariable("page") int page,@PathVariable("size") int size) {
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> test = new ArrayList<Map<String, Object>>();
        List<Blog> blogs = new ArrayList<Blog>();
        String title="";
        String fanName="";
        blogs = blogService.findAllByUserId(id);
        for(Blog blog:blogs) {
            title = blogService.findByBlogId(blog.getBlogId()).getTitle();
            for(Likes likes:likesService.findAllByBlogId(blog.getBlogId())){
                if(likes.getStatus()!=2) {
                    fanName = userService.findByUserId(likes.getUserId()).getNickname();
                    Map map = new HashMap();
                    map.put("likes", likes);
                    map.put("title", title);
                    map.put("fanName",fanName);
                    result.add(map);
                }
            }
        }
        for(int i=0;i<size;i++){
            if((i+size*page)<result.size())
                test.add(result.get(page*size+i));
        }
        return test;
    }
    @CrossOrigin
    @GetMapping(value = "/api/get_like_num/{id}")
    @ResponseBody
    public int get_like_num(@PathVariable int id) {
        int num=0;
        List<Blog> blogs = new ArrayList<Blog>();
        blogs = blogService.findAllByUserId(id);
        for(Blog blog:blogs) {
            for(Likes likes:likesService.findAllByBlogId(blog.getBlogId())){
                if(likes.getStatus()!=2)
                    num++;
            }
        }
        return num;
    }
    @CrossOrigin
    @GetMapping(value = "/api/del_like_info/{id}")
    @ResponseBody
    public void delete_like_info(@PathVariable int id) {
        Byte tag =2;
        Likes likes =likesService.findByLikeId(id);
        likes.setStatus(tag);
        likesService.save(likes);
    }
}

