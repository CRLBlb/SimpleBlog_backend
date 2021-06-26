package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.UserSettingsDAO;
import edu.hit.blogdemo.pojo.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService {
    @Autowired
    UserSettingsDAO userSettingsDAO;
    public UserSettings findAllByUserId(int userId){
        return userSettingsDAO.findAllByUserId(userId);
    }
    public void save(UserSettings userSettings){userSettingsDAO.save(userSettings);}
}
