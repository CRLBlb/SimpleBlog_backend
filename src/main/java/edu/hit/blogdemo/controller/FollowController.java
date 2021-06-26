package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.Follow;
import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.service.FollowService;
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
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;
    @CrossOrigin
    @GetMapping(value = "/api/get_follow/{id}/{page}/{size}")
    @ResponseBody
    List<Map<String,Object>> get_follow(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("size") int size){
        List<Map<String,Object>> result = new ArrayList<>();
        String fanName="";
        for(Follow follow:followService.findAllByStarId(id)){
            if(follow.getStatus()!=2) {
                fanName = userService.findByUserId(follow.getFanId()).getNickname();
                Map map = new HashMap<>();
                map.put("follow", follow);
                map.put("fanName", fanName);
                result.add(map);
            }
        }
        List<Map<String,Object>> test = new ArrayList<>();
        for(int i=0;i<size;i++){
            if((i+size*page)<result.size())
                test.add(result.get(page*size+i));
        }
        return test;
    }
    @CrossOrigin
    @GetMapping(value = "/api/get_follow_num/{id}")
    @ResponseBody
    public int get_comment_num(@PathVariable int id) {
        int num=0;
        for(Follow follow:followService.findAllByStarId(id)) {
            if(follow.getStatus()!=2)
                num++;
        }

        return num;
    }
    @CrossOrigin
    @GetMapping(value = "/api/del_follow_info/{id}")
    @ResponseBody
    public void delete_follow_info(@PathVariable int id) {
        Byte tag =2;
        Follow follow =followService.findByFollowId(id);
        follow.setStatus(tag);
        followService.save(follow);
    }

    //ypy
    @CrossOrigin
    @GetMapping("/api/follow/{fanid}")
    @ResponseBody
    public List<User> list(@PathVariable("fanid")int fanid) throws Exception{
        return followService.getfollowlist(fanid);
    }

    @CrossOrigin
    @GetMapping("/api/follow/isfollow/{fanid}/{starid}")
    @ResponseBody
    public Boolean isFollow(@PathVariable("fanid")int fanid,@PathVariable("starid")int starid) throws Exception{
        return followService.isFollow(fanid,starid);
    }

    @CrossOrigin
    @GetMapping("/api/follow/addfollow/{fanid}/{starid}")
    @ResponseBody
    public void addFollow(@PathVariable("fanid")int fanid,@PathVariable("starid")int starid) throws Exception{
        followService.add(fanid,starid);
    }

    @CrossOrigin
    @GetMapping("/api/follow/delfollow/{fanid}/{starid}")
    @ResponseBody
    public void delFollow(@PathVariable("fanid")int fanid,@PathVariable("starid")int starid) throws Exception{
        followService.del(fanid,starid);
    }
}
