package com.btl.n4j.services;

import com.btl.n4j.models.Field_TimeSlot;
import com.btl.n4j.repository.Field_TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Field_TimeSlotServiceImp implements Field_TimeSlotService{

    @Autowired
    private Field_TimeSlotRepository fieldTimeSlotRepository;

    @Override
    public List<Field_TimeSlot> getAll() {
        return this.fieldTimeSlotRepository.findAll();
    }

    @Override
    public Boolean createNew(Field_TimeSlot fieldTimeSlot) {
        try {
            this.fieldTimeSlotRepository.save(fieldTimeSlot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Field_TimeSlot findByID(Integer id) {
        return this.fieldTimeSlotRepository.findById(id).get();
    }

    @Override
    public Boolean update(Field_TimeSlot fieldTimeSlot) {
        try {
            this.fieldTimeSlotRepository.save(fieldTimeSlot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.fieldTimeSlotRepository.delete(findByID(id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
