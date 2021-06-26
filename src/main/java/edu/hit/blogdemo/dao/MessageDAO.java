package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDAO extends JpaRepository<MessageEntity, Integer> {
    public List<MessageEntity> findMessageEntitiesByReceiverIdAndSenderId(Integer receiver_id, Integer sender_id);

    public List<MessageEntity> findMessageEntityByMessageId(Integer message_id);

    @Query(value =
            "select distinct sender_id " +
                    "from message " +
                    "where receiver_id =?1  " +
                    "union select distinct receiver_id " +
                    "from message where sender_id =?1 ", nativeQuery = true)
    public List<Integer> findSessionById(Integer user_id);
    //设置消息为已读
    @Query(value = "update message set is_read = 1 " +
            "where (sender_id =?1 and receiver_id = ?2)",nativeQuery = true)
    public void readMessage(Integer sender_id, Integer receiver_id);
    public List<MessageEntity> findMessageEntitiesBySenderIdAndReceiverId(Integer sender_id, Integer receiver_id);
}
