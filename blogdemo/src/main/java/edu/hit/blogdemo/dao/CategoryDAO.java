package edu.hit.blogdemo.dao;

import edu.hit.blogdemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryDAO extends JpaRepository<Category, Integer> {
    Category findByCategoryId(int category_id);

}
