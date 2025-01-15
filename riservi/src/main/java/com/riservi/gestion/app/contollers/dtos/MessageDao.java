package com.riservi.gestion.app.contollers.dtos;

import com.riservi.gestion.app.service.dtos.ReservationDto;

public class MessageDao {

    private String code;

    private String message;

    private ReservationDto reservationDto;


    public MessageDao() {
    }

    public MessageDao(String code, String message, ReservationDto reservationDto) {
        this.code = code;
        this.message = message;
        this.reservationDto = reservationDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
