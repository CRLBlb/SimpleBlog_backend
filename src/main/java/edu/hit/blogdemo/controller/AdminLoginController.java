package edu.hit.blogdemo.controller;


import edu.hit.blogdemo.pojo.Admin;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
//@RequestMapping("api/admin")
public class AdminLoginController {

    @Autowired
    AdminService adminService;

    @CrossOrigin
    @PostMapping(value = "api/admin/login")
    // 此注解的作用是读取http请求的内容
    @ResponseBody
    public Result login(@RequestBody Admin admin) {
        // 对HTML标签进行转义，防止xss攻击
        // adminName = HtmlUtils.htmlEscape(adminName);
        Admin admin1 = adminService.get(admin.getAdminName(), admin.getAdminPassword());

        System.out.println(admin1);
        if (null == admin1) {
            String message = "账号密码错误";
            System.out.println(admin.getAdminName());
            return new Result(400);
        } else {
            return new Result(200);
        }
    }


}
