package com.btl.n4j.services;

import com.btl.n4j.models.Booking;
import com.btl.n4j.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Boolean createNew(Booking booking) {
        try {
            this.bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Booking findByID(Integer id) {
        return this.bookingRepository.findById(id).get();
    }

    @Override
    public Boolean update(Booking booking) {
        try {
            this.bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.bookingRepository.delete(findByID(id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
