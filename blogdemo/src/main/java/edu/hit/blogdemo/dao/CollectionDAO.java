package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionDAO extends JpaRepository<Collections,Integer> {

    //保存更新Collection数据
    public Collections save(Collections collection);
    //通过blog_id和user_id进行查询
    public Collections findByBlogIdAndUserId(int blodId,int userId);
    //根据collectionId删除某个收藏数据
    public void deleteByCollectionId(int collectionId);
}
