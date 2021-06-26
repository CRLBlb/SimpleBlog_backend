package edu.hit.blogdemo.pojo;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    private int categoryId;
    private String name;
    @Id
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
