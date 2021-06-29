package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.UserBroadcastDAO;
import edu.hit.blogdemo.pojo.UserBroadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBroadcastService {
    @Autowired
    UserBroadcastDAO userBroadcastDAO;
    public void save(UserBroadcast userBroadcast){userBroadcastDAO.save(userBroadcast);}
    //管理员方法
    public UserBroadcast adminSave(UserBroadcast userBroadcast){ return userBroadcastDAO.save(userBroadcast);}
    public List<UserBroadcast> findAllByUserId(int userId){return userBroadcastDAO.findAllByUserId(userId);}
    public Page<UserBroadcast> findAll(Pageable pageable){return userBroadcastDAO.findAll(pageable);}
    public UserBroadcast findByBroadcastIdAndUserId(int id,int bid){return userBroadcastDAO.findByBroadcastIdAndUserId(id,bid);}
    public UserBroadcast findById(int id){return userBroadcastDAO.findById(id);}
    public void deleteById(int id){userBroadcastDAO.deleteById(id);}

}
