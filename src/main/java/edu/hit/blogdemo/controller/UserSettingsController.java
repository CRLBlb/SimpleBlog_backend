package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.UserSettings;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserSettingsController {
    @Autowired
    UserSettingsService userSettingsService;
    @CrossOrigin
    @GetMapping(value = "/api/all/{is}/{id}")
    @ResponseBody
    public void set_all(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
            user.setIsBroadcast(tag);
            user.setBroadcastW(tag);
            user.setIsComment(tag);
            user.setCommentW(tag);
            user.setIsFollow(tag);
            user.setFollowW(tag);
            user.setIsLike(tag);
            user.setLikeW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/broadcast/{is}/{id}")
    @ResponseBody
    public void set_broadcast(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setIsBroadcast(tag);
        user.setBroadcastW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/broadcast_w/{is}/{id}")
    @ResponseBody
    public void set_broadcast_w(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setBroadcastW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/Iscomment/{is}/{id}")
    @ResponseBody
    public void set_comment(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setIsComment(tag);
        user.setCommentW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/comment_w/{is}/{id}")
    @ResponseBody
    public void set_comment_w(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setCommentW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/follow/{is}/{id}")
    @ResponseBody
    public void set_follow(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setIsFollow(tag);
        user.setFollowW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/follow_w/{is}/{id}")
    @ResponseBody
    public void set_follow_w(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setFollowW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/like/{is}/{id}")
    @ResponseBody
    public void set_like(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setIsLike(tag);
        user.setLikeW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/like_w/{is}/{id}")
    @ResponseBody
    public void set_like_w(@PathVariable("is") boolean is, @PathVariable("id") int id){
        UserSettings user=userSettingsService.findAllByUserId(id);
        byte tag=0;
        if(is==true)
            tag=1;
        user.setLikeW(tag);
        userSettingsService.save(user);
    }
    @CrossOrigin
    @GetMapping(value = "/api/get_settings/{id}")
    @ResponseBody
    public UserSettings get_settings(@PathVariable("id") int id){

        return userSettingsService.findAllByUserId(id);
    }
}
