package com.btl.n4j.services;

import com.btl.n4j.models.FieldType;
import com.btl.n4j.repository.FieldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldTypeServiceImp implements FieldTypeService {
    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    @Override
    public List<FieldType> getAll() {
        return this.fieldTypeRepository.findAll();
    }

    @Override
    public Boolean createNew(FieldType fieldType) {

        try {
            this.fieldTypeRepository.save(fieldType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public FieldType findByID(Integer id) {
        return this.fieldTypeRepository.findById(id).get();
    }

    @Override
    public Boolean update(FieldType fieldType) {
        try {
            this.fieldTypeRepository.save(fieldType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.fieldTypeRepository.delete(findByID(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
