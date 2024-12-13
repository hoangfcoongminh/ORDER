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
        return null;
    }

    @Override
    public Boolean update(Field field) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
