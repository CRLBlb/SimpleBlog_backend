package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.UserBroadcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBroadcastDAO extends JpaRepository<UserBroadcast,Integer> {
    List<UserBroadcast> findAllByUserId(int userId);
    UserBroadcast findByBroadcastIdAndUserId(int userId,int broadcastId);
    UserBroadcast findById(int id);
    void deleteById(int id);
}
