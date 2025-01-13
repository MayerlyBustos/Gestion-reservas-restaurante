package com.riservi.gestion.app.service.dtos;

import java.time.LocalTime;

public class ReservationDayDto {

    private int reservationId;

    private String fullName;

    private String numberPhone;

    private LocalTime hour;

    public ReservationDayDto() {
    }

    public ReservationDayDto(int reservationId, String fullName, String numberPhone, LocalTime hour) {
        this.reservationId = reservationId;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
        this.hour = hour;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
}
