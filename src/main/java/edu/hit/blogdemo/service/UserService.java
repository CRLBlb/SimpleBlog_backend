package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.UserDAO;
import edu.hit.blogdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;




    //通过用户手机号获取用户信息
    public User getByUserPhone(String userphone) {
        return userDAO.findByUserPhone(userphone);
    }
    //判断用户信息是否存在
    public boolean isExist(String userphone) {
        User user = getByUserPhone(userphone);
        return null!=user;
    }
    //通过用户手机和用户密码获取用户信息
    public User get(String userphone, String userpassword){
        return userDAO.getByUserPhoneAndUserPassword(userphone, userpassword);
    }
    //查询所有用户信息
    public List<User> searchAllUser(){
        return userDAO.findAll();
    }
    //新增用户信息
    public void addOrUpdate(User user) throws SQLIntegrityConstraintViolationException {
        userDAO.save(user);
    }
    //消息模块方法
    public  User findByUserId(int userId){return userDAO.findUserByUserId(userId);}
    public User getByName(String username) {
        return userDAO.findByUserPhone(username);
    }

    public User getByUserPhoneAndPassword(String username, String password){
        return userDAO.getByUserPhoneAndUserPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }


    public User getInfo(int userid){
        return userDAO.findByUserId(userid);
    }
    public void addOrSetUserInfo(User user){
        userDAO.save(user);
    }


}
