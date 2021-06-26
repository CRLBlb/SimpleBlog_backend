package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.BroadcastDAO;
import edu.hit.blogdemo.pojo.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastService {
    @Autowired
    BroadcastDAO broadcastDAO;
    public List<Broadcast> findAll(){
        return broadcastDAO.findAll();
    }
    public void save(Broadcast broadcast){ broadcastDAO.save(broadcast);}
    public Page<Broadcast> findAll(Pageable pageable){return broadcastDAO.findAll(pageable);}
    public Broadcast findByBroadcastId(int id){return broadcastDAO.findByBroadcastId(id);}
}
