package edu.hit.blogdemo.service;

import edu.hit.blogdemo.dao.CategoryDAO;
import edu.hit.blogdemo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int category_id) {
        Category category =categoryDAO.findByCategoryId(category_id);
        return category;
    }
}
