package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.*;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InformationController {
    @Autowired
    UserSettingsService userSettingsService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikesService likesService;
    @Autowired
    FollowService followService;
    @Autowired
    BlogService blogService;
    @Autowired
    BroadcastService broadcastService;
    @Autowired
    WebSocket webSocket;
    @Autowired
    UserBroadcastService userBroadcastService;
    @CrossOrigin
    @GetMapping(value = "/api/information/{id}")
    @ResponseBody
    //获取该id用户的所有消息的是否有未读状态
    Map<String, Boolean> isInformation(@PathVariable("id")int userId){
        Map<String,Boolean> result = new HashMap<String, Boolean>();
        result.put("comment",true);
        result.put("broadcast",true);
        result.put("like",true);
        result.put("follow",true);
        List<Blog> blogs = new ArrayList<Blog>();
        blogs = blogService.findAllByUserId(userId);
        for(Blog blog:blogs) {
            if(commentService.findAllByBlogId(blog.getBlogId())!=null) {
                for (Comment comment : commentService.findAllByBlogId(blog.getBlogId())) {
                    if (comment.getStatus() == 0 && userSettingsService.findAllByUserId(userId).getIsComment() == 1)
                    {
                        result.put("comment", false);
                        break;
                    }
                }
            }
            if(likesService.findAllByBlogId(blog.getBlogId())!=null) {
                for (Likes likes : likesService.findAllByBlogId(blog.getBlogId())) {
                    if (likes.getStatus() == 0 && userSettingsService.findAllByUserId(userId).getIsLike() == 1) {
                        result.put("like", false);
                        break;
                    }
                }
            }
        }
        if(userBroadcastService.findAllByUserId(userId)!=null) {
            for (UserBroadcast userBroadcast : userBroadcastService.findAllByUserId(userId)) {
                if (userBroadcast.getStatus() == 0 && userSettingsService.findAllByUserId(userId).getIsBroadcast() == 1) {
                    result.put("broadcast", false);
                    break;
                }
            }
        }
        if(followService.findAllByStarId(userId)!=null) {
            for (Follow follow : followService.findAllByStarId(userId)) {
                System.out.println(follow.getFollowId());
                if (follow.getStatus() == 0 && userSettingsService.findAllByUserId(userId).getIsFollow() == 1) {
                    result.put("follow", false);
                    break;
                }
            }
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/api/refresh_info/{type}/{id}")
    @ResponseBody
    public void refresh_comment(@PathVariable("id") int id,@PathVariable("type") String type){
        byte tag=1;
        if(type.equals("comment")) {
            for(Blog blog:blogService.findAllByUserId(id)){
                for(Comment comment:commentService.findAllByBlogId(blog.getBlogId())){
                    if(comment.getStatus()==0) {
                        comment.setStatus(tag);
                        commentService.save(comment);
                    }
                }
            }
        }
        if(type.equals("like")){
            for(Blog blog:blogService.findAllByUserId(id)){
                for(Likes likes:likesService.findAllByBlogId(blog.getBlogId())){
                    if(likes.getStatus()==0) {
                        likes.setStatus(tag);
                        likesService.save(likes);
                    }
                }
            }
        }
        if(type.equals("follow")) {
            for (Follow follow : followService.findAllByStarId(id)) {
                if(follow.getStatus()==0) {
                    follow.setStatus(tag);
                    followService.save(follow);
                }
            }
        }
        if(type.equals("broadcast")){
            for(UserBroadcast userBroadcast:userBroadcastService.findAllByUserId(id)){
                if(userBroadcast.getStatus()==0) {
                    userBroadcast.setStatus(tag);
                    userBroadcastService.save(userBroadcast);
                }
            }
        }
    }
}
