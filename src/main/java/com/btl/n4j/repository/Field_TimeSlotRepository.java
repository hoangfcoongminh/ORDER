package com.btl.n4j.repository;

import com.btl.n4j.models.Field_TimeSlot;
import com.btl.n4j.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Field_TimeSlotRepository extends JpaRepository<Field_TimeSlot, Integer> {
    @Query("SELECT f.timeSlot FROM Field_TimeSlot f WHERE f.field.fieldId = :fieldId")
    List<TimeSlot> findTimeSlotsByFieldId(@Param("fieldId") Integer fieldId);

}
