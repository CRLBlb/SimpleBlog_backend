package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.result.ResultFactory;
import edu.hit.blogdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("api/user/findbynickname/{size}/{page}")
    //分页根据文章标题查询用户信息
    public Result listUserByNickname(@PathVariable("size") int size
            , @PathVariable("page") int page
            , @RequestParam("nickname") String nickname) {
        // 关键词为空时查询出所有用户
        if ("".equals(nickname)) {
            System.out.println("查询所有用户信息!");
            return ResultFactory
                    .buildSuccessResult(userService.list(page - 1, size));
        }
        else {
            System.out.println("查询指定标题用户信息!");
            return ResultFactory
                    .buildSuccessResult(userService.
                            findAllByNicknameLike(page - 1, size,nickname) );
        }
    }
    @CrossOrigin
    @GetMapping("api/user/{userid}")
    public User getInfo(@PathVariable("userid")int userid){
        return userService.getInfo(userid);
    }

    @CrossOrigin
    @PostMapping("api/user/save")
    public void getInfo(@RequestBody User user){
        userService.addOrSetUserInfo(user);
    }

    //管理员模块
    // 测试通过
    @CrossOrigin
    @PostMapping(value = "api/admin/user/findAll")
    @ResponseBody
    public List<User> findAll(){
        return userService.findAll();
    }

    // 测试通过
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="api/admin/user/findById/{userId}")
    public User findById(@PathVariable(value="userId") int userId){
        return userService.findById(userId);
    }

    // 测试通过
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="api/admin/user/deleteById/{userId}")
    public Result deleteById(@PathVariable(value="userId") int userId){
        if(userService.deleteById(userId)){
            return new Result(200);
        }else {
            return new Result(400);
        }
    }
    // 通过评论的id和bool类型的审核结果修改数据库信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "api/admin/user/changeStatus/{userId}/{status}")
    public Result changeStatus(@PathVariable(value="userId")int userId,@PathVariable(value="status")Byte status){
        User user = userService.findById(userId);
        user.setStatus(status);
        user = userService.save(user);
        if(user!=null){
            return new Result(200);
        } else {
            return new Result(400);
        }
    }
}
