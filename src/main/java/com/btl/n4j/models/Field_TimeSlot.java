package com.btl.n4j.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "field_timeslot")
public class Field_TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_timeslot_id")
    private Integer fieldTimeslotId;

    @Column(name = "pricePerHour")
    private Integer pricePerHour;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @ManyToOne
    @JoinColumn(name = "timeSlot_id", nullable = false)
    private TimeSlot timeSlot;

    @OneToMany(mappedBy = "fieldTimeslot", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Field_TimeSlot() {
    }

    public Field_TimeSlot(Integer fieldTimeslotId, Integer pricePerHour, Boolean available, Field field, TimeSlot timeSlot, List<Booking> bookings) {
        this.fieldTimeslotId = fieldTimeslotId;
        this.pricePerHour = pricePerHour;
        this.available = available;
        this.field = field;
        this.timeSlot = timeSlot;
        this.bookings = bookings;
    }

    public Integer getFieldTimeslotId() {
        return fieldTimeslotId;
    }

    public void setFieldTimeslotId(Integer fieldTimeslotId) {
        this.fieldTimeslotId = fieldTimeslotId;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
