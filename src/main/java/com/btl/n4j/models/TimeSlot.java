package com.btl.n4j.models;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.List;


@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeSlot_id")
    private Integer timeSlotId;

    @Column(name = "pricePerHour")
    private Integer pricePerHour;

    @Column(name = "startTime", nullable = false)
    private Time startTime;

    @Column(name = "endTime", nullable = false)
    private Time endTime;

    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    private List<Field_TimeSlot> fieldTimeslots;

    public TimeSlot() {
    }

    public TimeSlot(Integer timeSlotId, Integer pricePerHour, Time startTime, Time endTime, List<Field_TimeSlot> fieldTimeslots) {
        this.timeSlotId = timeSlotId;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fieldTimeslots = fieldTimeslots;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public List<Field_TimeSlot> getFieldTimeslots() {
        return fieldTimeslots;
    }

    public void setFieldTimeslots(List<Field_TimeSlot> fieldTimeslots) {
        this.fieldTimeslots = fieldTimeslots;
    }
}
