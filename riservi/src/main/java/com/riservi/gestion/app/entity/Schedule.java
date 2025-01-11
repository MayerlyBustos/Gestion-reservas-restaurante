package com.riservi.gestion.app.entity;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="schedules")
public class Schedule {

    private int scheduleId;

    private Date date;

    private Date hour;

    private int available;

    private Reservation reservation;

    public Schedule() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "schedule_id", nullable = false, unique = true)
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "hour")
    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    @Column(name = "available", length = 1)
    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @OneToOne(mappedBy = "schedule", fetch = FetchType.LAZY)
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
