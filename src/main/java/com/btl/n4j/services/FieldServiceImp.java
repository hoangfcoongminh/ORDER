package com.btl.n4j.services;

import com.btl.n4j.models.Field;
import com.btl.n4j.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImp implements FieldService{

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public List<Field> getAll() {
        return this.fieldRepository.findAll();
    }

    @Override
    public Boolean createNew(Field field) {
        try {
            this.fieldRepository.save(field);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Field findByID(Integer id) {
        return this.fieldRepository.findById(id).get();
    }

    @Override
    public Boolean update(Field field) {
        try {
            this.fieldRepository.save(field);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.fieldRepository.delete(findByID(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Field> searchField(String keyword) {

        return this.fieldRepository.searchField(keyword);
    }

    @Override
    public List<Field> findByFieldTypeId(Integer fieldTypeId) {

        return this.fieldRepository.findByFieldTypeId(fieldTypeId);
    }
}
