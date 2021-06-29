package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Comment;
import edu.hit.blogdemo.result.Result;
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














    //管理员模块

    // 测试通过
    @CrossOrigin
    @PostMapping(value = "api/admin/comment/findAll")
    // 此注解的作用是读取http请求的内容
    @ResponseBody
    public List<Comment> findAll(){
        return commentService.findAll();
    }

    // 测试通过
    // 通过评论的id获取博客标题
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "api/admin/comment/findBlogByCommentId/{commentId}")
    public String findBlogByCommentId(@PathVariable(value="commentId")int commentId){
        Comment comment = commentService.findById(commentId);
        int blogId = comment.getBlogId();
        Blog blog = blogService.findByBlogId(blogId);
        return blog.getTitle();
    }


    // 通过评论的id和bool类型的审核结果修改数据库信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "api/admin/comment/changeStatus/{commentId}/{pass}")
    public Result changeStatus(@PathVariable(value="commentId")int commentId, @PathVariable(value="pass")boolean pass){
        Comment comment = commentService.findById(commentId);
        if(pass){
            comment.setPass((byte)1);
        }else {
            comment.setPass((byte)2);
        }
        comment = commentService.adminSave(comment);
        if(comment!=null){
            return new Result(200);
        } else {
            return new Result(400);
        }
    }




}
