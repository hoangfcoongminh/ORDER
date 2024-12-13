package com.btl.n4j.services;

import com.btl.n4j.models.Field;

import java.util.List;

public interface FieldService {
    List<Field> getAll();

    Boolean createNew(Field field);

    Field findByID(Integer id);

    Boolean update(Field field);

    Boolean delete(Integer id);
}
