package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.UserBroadcast;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.service.UserBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserBroadcastController {
    @Autowired
    UserBroadcastService userBroadcastService;

    // 测试通过
    @CrossOrigin
    @PostMapping(value = "api/admin/broadcast/createUserBroadcast")
    @ResponseBody
    public Result addBroadcast(@RequestBody UserBroadcast userbroadcast){
        UserBroadcast userBroadcast1 = userBroadcastService.adminSave(userbroadcast);
        if(userBroadcast1!=null){
            return new Result(200);
        }else {
            return new Result(400);
        }
    }
}
