package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.UserDAO;
import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.User;
import edu.hit.blogdemo.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    //分页查询所有博主信息
    public MyPage list(int page, int size) {
        MyPage<User> users;
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Page<User> usersInDb = userDAO.findAll(PageRequest.of(page, size, sort));
        users = new MyPage<>(usersInDb);
        return users;
    }

    //通过用户昵称进行分页查询
    public MyPage findAllByNicknameLike(int page, int size, String  nickname){
        MyPage<User> users;
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Page<User> usersInDb = userDAO.findAllByNicknameLike(PageRequest.of(page, size, sort)
                ,'%' + nickname + '%');
        users = new MyPage<>(usersInDb);
        return users;
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

    public List<User> findAll() {
        return userDAO.findAll();
    }


    public User findById(int userId) {
        return userDAO.findById(userId).get();
    }


    public User edit(User user) {
        return userDAO.save(user);
    }


    public boolean deleteById(int userId) {
        boolean result = true;
        try
        {
            userDAO.deleteById(userId);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    public User save(User user) {
        return userDAO.save(user);
    }
}
