package edu.hit.blogdemo.controller;


import edu.hit.blogdemo.pojo.MessageEntity;
import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @CrossOrigin
    @GetMapping("/api/chat")
    @ResponseBody
    public List<MessageEntity>getSession(@RequestParam("senderId") Integer send_id,
                                           @RequestParam("receiverId") Integer receiver_id){
        return messageService.getSession(receiver_id,send_id);
    }
// 发送消息
    @CrossOrigin
    @PostMapping("/api/addMessage")
    @ResponseBody
    public MessageEntity addMessage(@RequestBody MessageEntity requestMessage){
        requestMessage.setMessageCreateTime(new Timestamp(System.currentTimeMillis()));
        requestMessage.setIsRead((byte) 0);
        messageService.addMessage(requestMessage);
        MessageEntity res = new MessageEntity();
        res = requestMessage;
        return res;
    }

    //得到会话用户列表
    @CrossOrigin
    @GetMapping("/api/getSessionByUserId")
    @ResponseBody
    public List<User> getSessionByUserId(@RequestParam("id") Integer user_id){
        List<User> result = messageService.fff(user_id);
        return result;
    }
    //得到最新消息
    @CrossOrigin
    @GetMapping("/api/getLastMessage")
    @ResponseBody
    public MessageEntity getLastMessage(@RequestParam("senderId") Integer sender_id,
                                        @RequestParam("receiverId") Integer receiver_id){
        MessageEntity result = messageService.getLastMessage(receiver_id,sender_id);
        return result;
    }
    //得到未读消息数
    @CrossOrigin
    @GetMapping("/api/getUnreadMessageNum")
    @ResponseBody
    public int getUnreadMessageNum(@RequestParam("senderId") Integer sender_id,
                                  @RequestParam("receiverId") Integer receiver_id){
        int num = messageService.getUnreadMessageNum(receiver_id,sender_id);
        Map<String ,Integer> result = new HashMap<>();
        result.put(sender_id.toString(),num);
        System.out.println(result);
        return num;
    }

    //发起会话
    @CrossOrigin
    @GetMapping("/api/addSession")
    @ResponseBody
    public boolean addSession(@RequestParam("senderId") Integer sender_id,
                           @RequestParam("receiverId") Integer receiver_id){
        if(getSession(sender_id,receiver_id).size()==0){
            MessageEntity message = new MessageEntity();
            message.setIsRead((byte) 0);
            message.setMessageCreateTime(new Timestamp(System.currentTimeMillis()));
            message.setContent("你好，快来跟我打个招呼吧！");
            message.setSenderId(sender_id);
            message.setReceiverId(receiver_id);
            messageService.addMessage(message);
        }
        return true;
    }

    @CrossOrigin
    @GetMapping("/api/readMessageOfUserId")
    public void readMessageOfUserId(@RequestParam("senderId") Integer sender_id,
                                    @RequestParam("receiverId") Integer receiver_id){
        messageService.readMessage(sender_id, receiver_id);
    }
}
