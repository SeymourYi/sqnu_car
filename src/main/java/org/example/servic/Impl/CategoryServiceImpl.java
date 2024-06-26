package org.example.servic.Impl;

import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.servic.CategoryService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =(Integer) map.get("id");
        category.setCreateUser(userId);
        System.out.println(category);
        categoryMapper.add(category);

    }

    @Override
    public List<Category> list() {
       Map<String,Object> map = ThreadLocalUtil.get();
//       Integer userId = (Integer) map.get("id");
        return categoryMapper.list();
    }

    @Override
    public Category findById(@RequestBody Integer id) {
     Category c = categoryMapper.findById(id);
        return c;
    }

    @Override
    public void update( Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }
}
