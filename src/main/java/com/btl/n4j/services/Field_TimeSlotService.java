package com.btl.n4j.services;

import com.btl.n4j.models.Field_TimeSlot;
import com.btl.n4j.models.TimeSlot;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Field_TimeSlotService {
    List<Field_TimeSlot> getAll();

    Boolean createNew(Field_TimeSlot fieldTimeSlot);

    Field_TimeSlot findByID(Integer id);

    Boolean update(Field_TimeSlot fieldTimeSlot);

    Boolean delete(Integer id);

    Field_TimeSlot findByFieldAndTimeId(Integer fieldId, Integer timeSlotId);

    List<TimeSlot> findTimeSlotAvaibleByFieldId(Integer fieldId);
}
