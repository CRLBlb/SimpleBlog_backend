package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/{userid}")
    public User getInfo(@PathVariable("userid")int userid){
        return userService.getInfo(userid);
    }

    @CrossOrigin
    @PostMapping("/save")
    public void getInfo(@RequestBody User user){
        userService.addOrSetUserInfo(user);
    }
}
