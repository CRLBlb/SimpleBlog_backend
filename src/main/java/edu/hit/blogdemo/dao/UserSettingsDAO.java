package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsDAO extends JpaRepository<UserSettings,Integer> {
    UserSettings findAllByUserId(int userId);
}
