package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.UserSettings;
import edu.hit.blogdemo.service.BlogService;
import edu.hit.blogdemo.service.UserSettingsService;
import edu.hit.blogdemo.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class WebSockerController {
    @Autowired
    WebSocket webSocket;
    @Autowired
    UserSettingsService userSettingsService;

    @Autowired
    BlogService blogService;
    @ResponseBody
    @GetMapping("api/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        webSocket.sendAllMessage(text);
        return text;
    }
    @ResponseBody
    @GetMapping("api/sendOneWebSocket/{blogId}/{type}")
    public String sendOneWebSocket(@PathVariable("blogId")int blogId,@PathVariable("type")String type) {
        int userId = blogService.findByBlogId(blogId).getUserId();
        String userName = Integer.toString(userId);
        try {
            if((type.equals("broadcast")&&userSettingsService.findAllByUserId(userId).getBroadcastW()==1)
                    ||(type.equals("like")&&userSettingsService.findAllByUserId(userId).getLikeW()==1)
                    ||(type.equals("follow")&&userSettingsService.findAllByUserId(userId).getFollowW()==1)
                    ||(type.equals("comment")&&userSettingsService.findAllByUserId(userId).getCommentW()==1))
                webSocket.sendOneMessage(userName,type);
            if((type.equals("broadcast")&&userSettingsService.findAllByUserId(userId).getIsBroadcast()==1)
                    ||(type.equals("like")&&userSettingsService.findAllByUserId(userId).getIsLike()==1)
                    ||(type.equals("follow")&&userSettingsService.findAllByUserId(userId).getIsFollow()==1)
                    ||(type.equals("comment")&&userSettingsService.findAllByUserId(userId).getIsComment()==1))
                webSocket.sendOneMessage(userName,type+"w");
        }catch(Exception e){
            System.out.println("该用户未连接");
        }
        return type;
    }
}
