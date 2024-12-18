package com.btl.n4j.services;

import com.btl.n4j.models.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    Boolean createNew(Booking booking);

    Booking findByID(Integer id);

    Boolean update(Booking booking);

    Boolean delete(Integer id);
}
