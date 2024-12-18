package com.btl.n4j.services;

import com.btl.n4j.models.TimeSlot;
import com.btl.n4j.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotServiceImp implements TimeSlotService{

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> getAll() {
        return this.timeSlotRepository.findAll();
    }

    @Override
    public Boolean createNew(TimeSlot timeSlot) {

        try {
            this.timeSlotRepository.save(timeSlot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TimeSlot findByID(Integer id) {

        return this.timeSlotRepository.findById(id).get();
    }

    @Override
    public Boolean update(TimeSlot timeSlot) {

        try {
            this.timeSlotRepository.save(timeSlot);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {

        try {
            this.timeSlotRepository.delete(findByID(id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
