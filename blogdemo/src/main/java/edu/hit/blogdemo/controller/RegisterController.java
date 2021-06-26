package edu.hit.blogdemo.controller;
import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.result.ResultFactory;
import edu.hit.blogdemo.service.UserService;
import edu.hit.blogdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("api/searchuser")
    //搜索所有用户信息
    public Result searchUser() {
        return ResultFactory.buildSuccessResult(userService.searchAllUser());
    }

    //新增或更新经纪人信息
    @CrossOrigin
    @PostMapping("api/userregister")
    @ResponseBody
    public User addUser(@RequestBody User user) throws SQLIntegrityConstraintViolationException{
        System.out.println("the result is:"+user.getUserPhone());
        System.out.println("the result is:"+user.getUserPassword());
        try {
            userService.addOrUpdate(user);
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("用户名重复！");
        }
        return user;
    }
    @CrossOrigin
    @PostMapping("api/useravatar")
    public String avatarUpload(MultipartFile file) throws Exception {
        String folder = "D:/IDEAProjects/housingproject-iview/src/assets/images";
        File imageFolder = new File(folder);
        File f = new File(imageFolder
                , StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
