package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.repository.IReservationRepository;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import com.riservi.gestion.app.service.dtos.ReservationRequestDto;
import com.riservi.gestion.app.service.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public List<ReservationDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return  Util.mappearReservation(reservations);
    }

    @Override
    public ReservationDto insertReservation(Reservation reservation) {
    if(reservation != null){
        Reservation data = reservationRepository.save(reservation);
        return Util.mappearReservationDto(data);

     }
        return null;
    }

    @Override
    public ReservationDto findById(int reservationId) {
        Optional<Reservation> data = reservationRepository.findById(reservationId);
        return data.map(Util::mappearReservation).orElse(null);
    }

    @Override
    public List<ReservationDto> findByCustomerId(int customerId) {
            List<Reservation> data = reservationRepository.findByCustomerId(customerId);
            if(!data.isEmpty()){
                return Util.mappearReservation(data);
            }
            return null;
        }

    @Override
    public ReservationDto findByScheduleId(int scheduleId) {
        Reservation data = reservationRepository.findByScheduleId(scheduleId);
        if(data != null){
            return Util.mappearReservation(data);
        }
        return null;
    }

    @Override
    public void updateReservation(ReservationDto reservationDto) {

    }

    @Override
    public void deleteReservation(int reservationId) {

    }

}
