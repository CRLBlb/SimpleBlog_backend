package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.result.ResultFactory;
import edu.hit.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String userphone = requestUser.getUserPhone();
        userphone = HtmlUtils.htmlEscape(userphone);

        User user = userService.get(userphone, requestUser.getUserPassword());
        if (null == user) {
            System.out.println("登录失败！");
            return ResultFactory.buildFailResult("密码错误");
        }
        else {
            //判断用户是否加入黑名单
            //0:加入黑名单 1：未加入黑名单
            //用户被加入黑名单
            if(user.getStatus()==0){
                System.out.println("登录失败，用户已被冻结！");
                return ResultFactory.buildFailResult("用户被冻结");
            }
            //用户未加入黑名单
            else{
                System.out.println("登录成功！");
                System.out.println(user);
                return ResultFactory.buildSuccessResult(user);
            }
        }
    }
    @CrossOrigin
    @GetMapping("/api/logout")
    public Result logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        //前端清楚storage数据
        return ResultFactory.buildSuccessResult("成功登出");
    }
}