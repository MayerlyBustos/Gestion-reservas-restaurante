package com.riservi.gestion.app.service.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {

    private int scheduleId;

    private LocalDate date;

    private LocalTime hour;

    private int available;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
