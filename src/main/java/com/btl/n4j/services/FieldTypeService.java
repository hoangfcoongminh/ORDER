package com.btl.n4j.services;

import com.btl.n4j.models.FieldType;

import java.util.List;

public interface FieldTypeService {
    List<FieldType> getAll();

    Boolean createNew(FieldType fieldType);

    FieldType findByID(Integer id);

    Boolean update(FieldType fieldType);

    Boolean delete(Integer id);
}
