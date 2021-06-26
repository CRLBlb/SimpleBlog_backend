package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BroadcastDAO extends JpaRepository<Broadcast,Integer> {
    List<Broadcast> findAll();
    Broadcast findByBroadcastId(int id);
}
