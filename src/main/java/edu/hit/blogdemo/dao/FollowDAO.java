package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowDAO extends JpaRepository<Follow,Integer> {
    List<Follow> findAllByStarId(int starId);
    Follow findByFollowId(int followId);

    List<Follow> findByFanId(int fanid);
    void deleteByFanIdAndStarId(int fanid, int starid);
}
