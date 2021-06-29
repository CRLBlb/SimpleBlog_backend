package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.MessageDAO;
import edu.hit.blogdemo.dao.UserDAO;
import edu.hit.blogdemo.pojo.MessageEntity;
import edu.hit.blogdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;
    @Autowired
    UserDAO userDAO;

    public List<MessageEntity> getSession(Integer receiver_id, Integer sender_id){
        List<MessageEntity> result = messageDAO.findMessageEntitiesByReceiverIdAndSenderId(receiver_id,sender_id);
        result.addAll(messageDAO.findMessageEntitiesByReceiverIdAndSenderId(sender_id,receiver_id));
        Collections.sort(result);
        return result;
    }

    public Integer getUnreadMessageNum(Integer receiver_id, Integer sender_id){
        List<MessageEntity> result = messageDAO.findMessageEntitiesByReceiverIdAndSenderId(receiver_id,sender_id);
        int num = 0;
        for(MessageEntity message:result){
            if(message.getIsRead()==0){
                num++;
            }
        }
        return num;
    }
    public MessageEntity getLastMessage(Integer receiver_id, Integer sender_id){
        List<MessageEntity> result = messageDAO.findMessageEntitiesByReceiverIdAndSenderId(receiver_id,sender_id);
        result.addAll(messageDAO.findMessageEntitiesByReceiverIdAndSenderId(sender_id,receiver_id));
        Collections.sort(result);

        return result.get(result.size()-1);
    }


    public List<MessageEntity> getMessage(Integer message_id){
        List<MessageEntity> result = messageDAO.findMessageEntityByMessageId(message_id);
        return result;
    }

    public void addMessage(MessageEntity messageEntity){
        messageDAO.save(messageEntity);
    }

    public List<User> fff(Integer user_id){
        List<User> result= userDAO.findAllByUserIdIn(messageDAO.findSessionById(user_id));
        return result;
    }

    public void readMessage(Integer sender_id, Integer receiver_id){
        List<MessageEntity> result= messageDAO.findMessageEntitiesBySenderIdAndReceiverId(sender_id,receiver_id);
        for(MessageEntity m:result){
            m.setIsRead((byte) 1);
            messageDAO.save(m);
        }
    }

    public boolean checkMessage(Integer id) {
        int num = messageDAO.findAllByReceiverIdAndIsRead(id,(byte) 0).size();
        return num != 0;
    }
}
