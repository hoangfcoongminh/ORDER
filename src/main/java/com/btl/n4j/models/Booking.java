package com.btl.n4j.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "date")
    private Date date;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @Column(name = "note", length = 100)
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "field_timeslot_id")
    private Field_TimeSlot fieldTimeslot;

    public Booking() {
    }

    public Booking(Integer bookingId, Date date, Integer totalPrice, String note, User user, Field_TimeSlot fieldTimeslot) {
        this.bookingId = bookingId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.note = note;
        this.user = user;
        this.fieldTimeslot = fieldTimeslot;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Field_TimeSlot getFieldTimeslot() {
        return fieldTimeslot;
    }

    public void setFieldTimeslot(Field_TimeSlot fieldTimeslot) {
        this.fieldTimeslot = fieldTimeslot;
    }
}
