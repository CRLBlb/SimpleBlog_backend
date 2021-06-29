package edu.hit.blogdemo.controller;
import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.pojo.UserSettings;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.result.ResultFactory;
import edu.hit.blogdemo.service.UserService;
import edu.hit.blogdemo.service.UserSettingsService;
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
    @Autowired
    UserSettingsService userSettingsService;

    @CrossOrigin
    @GetMapping("api/searchuser")
    //搜索所有用户信息
    public Result searchUser() {
        return ResultFactory.buildSuccessResult(userService.searchAllUser());
    }

    //新增或更新用户信息
    @CrossOrigin
    @PostMapping("api/userregister")
    @ResponseBody
    public User addUser(@RequestBody User user) throws SQLIntegrityConstraintViolationException{
        System.out.println("the result is:"+user.getUserPhone());
        System.out.println("the result is:"+user.getUserPassword());
        try {
            //注册用户信息
            userService.addOrUpdate(user);
            //获取插入数据的Id值
            int userId = userService.getByUserPhone(user.getUserPhone()).getUserId();
            //生成默认消息设置
            byte tag =1;
            UserSettings userSettings = new UserSettings();
            userSettings.setUserId(userId);
            userSettings.setIsLike(tag);
            userSettings.setIsFollow(tag);
            userSettings.setIsBroadcast(tag);
            userSettings.setIsComment(tag);
            userSettings.setLikeW(tag);
            userSettings.setFollowW(tag);
            userSettings.setCommentW(tag);
            userSettings.setBroadcastW(tag);
            userSettingsService.save(userSettings);
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("用户名重复！");
        }
        return user;

    }
    //新增或更新用户信息
    @CrossOrigin
    @GetMapping("api/userregistersetting/{userId}")
    @ResponseBody
    public UserSettings addUserSettings(@PathVariable("userId") int userId){
//        System.out.println("the result is:"+user.getUserPhone());
//        System.out.println("the result is:"+user.getUserPassword());
        UserSettings userSettings = new UserSettings();
        byte tag = 1;
        try {
            //生成默认消息设置
            userSettings.setUserId(userId);
            userSettings.setBroadcastW(tag);
            userSettings.setCommentW(tag);
            userSettings.setFollowW(tag);
            userSettings.setLikeW(tag);
            userSettings.setIsBroadcast(tag);
            userSettings.setIsComment(tag);
            userSettings.setIsFollow(tag);
            userSettings.setIsLike(tag);
            userSettingsService.save(userSettings);
        }catch(Exception e){
            System.out.println("添加用户设置失败！");
        }
        return userSettings;
    }




    @CrossOrigin
    @PostMapping("api/useravatar")
    //上传用户头像
    public String avatarUpload(MultipartFile file) throws Exception {
        String folder = "D:/vue/img";
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
