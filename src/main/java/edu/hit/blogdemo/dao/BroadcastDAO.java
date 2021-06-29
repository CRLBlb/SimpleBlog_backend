package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BroadcastDAO extends JpaRepository<Broadcast,Integer> {
    List<Broadcast> findAll();
    Broadcast findByBroadcastId(int id);
    @Query(value = "select max(broadcast_id) from broadcast",nativeQuery = true)
    int findMaxId();

}
