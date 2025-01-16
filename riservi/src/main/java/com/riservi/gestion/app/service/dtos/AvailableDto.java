package com.riservi.gestion.app.service.dtos;

import java.time.LocalTime;

public class AvailableDto {

    private int id;

    private LocalTime hour;

    public AvailableDto() {
    }

    public AvailableDto(LocalTime hour) {
        this.hour = hour;
    }

    public AvailableDto(int id, LocalTime hour) {
        this.id = id;
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

}
