package com.riservi.gestion.app.contollers.dtos;

public class UpdateReservationRequest {

    private int scheduleId;

    public UpdateReservationRequest() {
    }

    public UpdateReservationRequest(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
