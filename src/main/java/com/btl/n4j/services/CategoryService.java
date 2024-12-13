package com.btl.n4j.services;

import com.btl.n4j.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Boolean createNew(Category category);

    Category findByID(Integer id);

    Boolean update(Category category);

    Boolean delete(Integer id);
}
