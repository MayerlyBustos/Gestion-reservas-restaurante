package com.riservi.gestion.app.service;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.service.dtos.ReservationDayDto;
import com.riservi.gestion.app.service.dtos.ReservationDto;

import java.util.List;

public interface IReservationService {

    List<ReservationDto> getAll();

    ReservationDto insertReservation(Reservation requestDto);

    ReservationDto findById(int reservationId);

    List<ReservationDto> findByCustomerId(int customerId);

    ReservationDto findByScheduleId(int scheduleId);

    List<ReservationDayDto> listByDay(String day);

    ReservationDto updateReservation(int id, int schedule);

    boolean deleteReservation(int reservationId);
}
