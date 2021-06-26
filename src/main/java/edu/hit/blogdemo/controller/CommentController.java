package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Comment;
import edu.hit.blogdemo.service.BlogService;
import edu.hit.blogdemo.service.CommentService;
import edu.hit.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;
    @CrossOrigin
    @GetMapping(value = "/api/get_comment/{id}/{page}/{size}")
    @ResponseBody
    public List<Map<String,Object>> get_comment(@PathVariable int id,@PathVariable("page") int page,@PathVariable("size") int size) {
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> test = new ArrayList<Map<String, Object>>();
        List<Blog> blogs = new ArrayList<Blog>();
        String title="";
        blogs = blogService.findAllByUserId(id);
        for(Blog blog:blogs) {
            title = blogService.findByBlogId(blog.getBlogId()).getTitle();
            for(Comment comment:commentService.findAllByBlogId(blog.getBlogId())){
                Map map =new HashMap();
                if(comment.getStatus()!=2) {
                    map.put("comment", comment);
                    map.put("userName", userService.findByUserId(comment.getUserId()).getNickname());
                    map.put("title", title);
                    result.add(map);
                }
            }
        }
            for(int i=0;i<size;i++){
                if((i+size*page)<result.size())
                test.add(result.get(page*size+i));
            }
            System.out.println("ok1121");
        return test;
    }
    @CrossOrigin
    @GetMapping(value = "/api/get_comment_num/{id}")
    @ResponseBody
    public int get_comment_num(@PathVariable int id) {
        int num=0;
        List<Blog> blogs = new ArrayList<Blog>();
        blogs = blogService.findAllByUserId(id);
        for(Blog blog:blogs) {
            for(Comment comment:commentService.findAllByBlogId(blog.getBlogId())){
                if(comment.getStatus()!=2)
                    num++;
            }
        }
        System.out.println("111111");
        return num;
    }
    @CrossOrigin
    @GetMapping(value = "/api/del_comment_info/{id}")
    @ResponseBody
    public void delete_comment_info(@PathVariable int id) {
        Byte tag =2;
        Comment comment =commentService.findByCommentId(id);
        comment.setStatus(tag);
        commentService.save(comment);
    }
}
