package com.riservi.gestion.app.service.dtos;

import java.time.LocalTime;

public class AvailableDto {

    private LocalTime hour;

    public AvailableDto() {
    }

    public AvailableDto(LocalTime hour) {
        this.hour = hour;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

}
