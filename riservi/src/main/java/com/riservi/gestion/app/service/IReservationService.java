package com.riservi.gestion.app.service;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.service.dtos.ReservationDto;

import java.util.List;

public interface IReservationService {

    List<ReservationDto> getAll();
    Reservation insertReservation(ReservationDto reservationDto);

    ReservationDto findById(int reservationId);

    ReservationDto findByCustomerId(int customerId);

    ReservationDto findByScheduleId(int scheduleId);

    void updateReservation(ReservationDto reservationDto);

    void deleteReservation(int reservationId);
}
