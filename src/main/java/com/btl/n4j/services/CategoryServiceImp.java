package com.btl.n4j.services;

import com.btl.n4j.models.Category;
import com.btl.n4j.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Boolean createNew(Category category) {

        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findByID(Integer id) {

        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Boolean update(Category category) {

        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {

        try {
            this.categoryRepository.delete(findByID(id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
