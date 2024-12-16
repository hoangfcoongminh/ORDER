package com.btl.n4j.repository;

import com.btl.n4j.models.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    @Query("SELECT f FROM Field f WHERE f.fieldName LIKE %:keyword% OR f.address LIKE %:keyword%")
    List<Field> searchField(@Param("keyword") String keyword);

    @Query("SELECT f FROM Field f WHERE f.fieldType.id = :fieldTypeId")
    List<Field> findByFieldTypeId(@Param("fieldTypeId") Integer fieldTypeId);
}
