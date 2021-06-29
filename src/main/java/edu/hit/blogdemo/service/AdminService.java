package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.AdminDao;
import edu.hit.blogdemo.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;


    public Admin get(String adminName, String adminPassword) {
        return adminDao.getByAdminNameAndAdminPassword(adminName,adminPassword);
    }

    public Admin getById(String adminName) {
        return adminDao.findByAdminName(adminName);
    }


    public List<Admin> findAll() {
        return adminDao.findAll();
    }
}
