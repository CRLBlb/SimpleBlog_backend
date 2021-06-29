package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.FollowDAO;
import edu.hit.blogdemo.dao.UserDAO;
import edu.hit.blogdemo.pojo.Follow;
import edu.hit.blogdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
//删除需要添加注解
@Transactional
public class FollowService {
    @Autowired
    FollowDAO followDAO;
    @Autowired
    UserDAO userDAO;

    public List<Follow> findAllByStarId(int starId) {
        return followDAO.findAllByStarId(starId);
    }

    public void save(Follow follow) {
        followDAO.save(follow);
    }

    public Follow findByFollowId(int id) {
        return followDAO.findByFollowId(id);
    }


    public List<User> getfollowlist(int fanid) {
        List<Follow> list = followDAO.findByFanId(fanid);
        int i = 0;
        User user;
        List<User> rtnList = new ArrayList<>();
        for (i = 0; i < list.size(); i++) {
            rtnList.add(userDAO.findByUserId(list.get(i).getStarId()));
        }
        return rtnList;
    }

    public Boolean isFollow(int fanid, int starid) {
        List<Follow> list = followDAO.findByFanId(fanid);
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getStarId() == starid) {
                return true;
            }
        }
        return false;
    }

    public void add(int fanid, int starid) {

        Follow follow = new Follow();
        follow.setFanId(fanid);
        follow.setStarId(starid);
        Timestamp createTime = new Timestamp(new Date().getTime());
        follow.setFollowCreatTime(createTime);
        Byte b = 0;
        follow.setStatus(b);

        followDAO.save(follow);
    }

    public void del(int fanid, int starid) {
        followDAO.deleteByFanIdAndStarId(fanid, starid);
    }
}
