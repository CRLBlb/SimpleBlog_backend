package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Blog;
import edu.hit.blogdemo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUserPhone(String userphone);
    User getByUserPhoneAndUserPassword(String userphone, String userpassword);
    //查询所有用户信息
    List<User> findAll();
    //保存更新User数据
    public User save(User user);
    //通过用户昵称分页模糊查询用户
    Page<User> findAllByNicknameLike(Pageable var1,String nickname);

    User findUserByUserId(int userId);
    //ypy
    User findByUserId(int userId);
    //zsy
    public List<User> findAllByUserIdIn(List<Integer> id);


}
