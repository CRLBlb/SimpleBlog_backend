package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.Broadcast;
import edu.hit.blogdemo.pojo.Comment;
import edu.hit.blogdemo.pojo.UserBroadcast;
import edu.hit.blogdemo.service.BroadcastService;
import edu.hit.blogdemo.service.UserBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BroadcastController {
    @Autowired
    UserBroadcastService userBroadcastService;
    @Autowired
    BroadcastService broadcastService;

    @CrossOrigin
    @GetMapping(value = "/api/get_broadcast/{id}/{page}/{size}")
    @ResponseBody
    public List<Map<String, Object>> get_broadcast(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("size") int size) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> test = new ArrayList<>();
        int Id = 0;
        for (UserBroadcast userBroadcast : userBroadcastService.findAllByUserId(id)) {
            Map<String, Object> map = new HashMap<>();
            Id = userBroadcast.getId();
            map.put("id", Id);
            map.put("broadcast", broadcastService.findByBroadcastId(userBroadcast.getBroadcastId()));
            result.add(map);
        }
        for (int i = 0; i < size; i++) {
            if ((i + size * page) < result.size())
                test.add(result.get(page * size + i));
        }
        return test;
    }

    @CrossOrigin
    @GetMapping(value = "/api/get_broadcast_num/{id}")
    @ResponseBody
    public int get_comment_num(@PathVariable int id) {
        int num = 0;
        for (UserBroadcast userBroadcast : userBroadcastService.findAllByUserId(id)) {
            num++;
        }

        return num;
    }

    @CrossOrigin
    @GetMapping(value = "/api/del_broadcast_info/{id}")
    @ResponseBody
    public void delete_broadcast_info(@PathVariable("id") int id) {
        userBroadcastService.deleteById(id);
    }

    //管理员模块
    // 测试通过
    @CrossOrigin
    @PostMapping(value = "api/admin/broadcast/findAll")
    @ResponseBody
    public List<Broadcast> findAll() {
        return broadcastService.findAll();
    }

    // 测试通过(返回broadcastId)
    @CrossOrigin
    @PostMapping(value = "api/admin/broadcast/createBroadcast")
    @ResponseBody
    public int addBroadcast(@RequestBody Broadcast broadcast){
        Broadcast broadcast1 = broadcastService.createBroadcast(broadcast);
        System.out.println(broadcastService.findMaxId());
        if(broadcast1!=null){
            return broadcastService.findMaxId();
        }else {
            return -1;
        }
    }

    // 测试通过
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "api/admin/broadcast/findBroadcastById/{broadcastId}")
    public Broadcast findBroadcastById(@PathVariable(value = "broadcastId") int broadcastId) {
        return broadcastService.findBroadcastById(broadcastId);
    }
}