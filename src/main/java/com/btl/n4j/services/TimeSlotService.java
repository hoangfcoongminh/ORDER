package com.btl.n4j.services;

import com.btl.n4j.models.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    List<TimeSlot> getAll();

    Boolean createNew(TimeSlot timeSlot);

    TimeSlot findByID(Integer id);

    Boolean update(TimeSlot timeSlot);

    Boolean delete(Integer id);
}
