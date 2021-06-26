package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.CollectionDAO;
import edu.hit.blogdemo.pojo.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//删除需要添加注解
@Transactional
public class CollectionsService {
    @Autowired
    CollectionDAO collectionDAO;
    //更新用户-收藏数据
    public void addOrUpdate(Collections collections) {
        collectionDAO.save(collections);
    }
    //通过blogId和userId查询用户-收藏数据
    public Collections findByBlogIdAndUserId(int blogId, int userId){
        return collectionDAO.findByBlogIdAndUserId(blogId,userId);
    }
    //查询所有用户-收藏数据信息
    public List<Collections> searchAllUserCollections(){
        return collectionDAO.findAll();
    }
    //通过collectionId删除某个用户-收藏数据信息
    public void deleteUserCollections(int collectionId){
        collectionDAO.deleteByCollectionId(collectionId);
    }
}
