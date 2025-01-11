package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.repository.IReservationRepository;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.dtos.ReservationDto;
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
    public Reservation insertReservation(ReservationDto reservationDto) {
        return null;
    }

    @Override
    public ReservationDto findById(int reservationId) {
        Optional<Reservation> data = reservationRepository.findById(reservationId);
        if(data.isPresent()){
            return Util.mappearReservation(data.get());
        }
        return null;
    }

    @Override
    public ReservationDto findByCustomerId(int customerId) {
        
        return null;
    }

    @Override
    public ReservationDto findByScheduleId(int scheduleId) {
        return null;
    }

    @Override
    public void updateReservation(ReservationDto reservationDto) {

    }

    @Override
    public void deleteReservation(int reservationId) {

    }
}
