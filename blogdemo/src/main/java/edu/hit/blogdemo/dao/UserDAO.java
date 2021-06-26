package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUserPhone(String userphone);
    User getByUserPhoneAndUserPassword(String userphone, String userpassword);
    //查询所有用户信息
    List<User> findAll();
    //保存更新User数据
    public User save(User user);

}
