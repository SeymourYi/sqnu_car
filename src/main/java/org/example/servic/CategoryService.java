package org.example.servic;

import org.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    public   void update(Category category);
}
