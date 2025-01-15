package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.repository.IReservationRepository;
import com.riservi.gestion.app.repository.IScheduleRepository;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.dtos.ReservationDayDto;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import com.riservi.gestion.app.service.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Override
    public List<ReservationDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return  Util.mappearReservation(reservations);
    }

    @Override
    public ReservationDto insertReservation(Reservation reservation) {
    if(reservation != null){
        Reservation data = reservationRepository.save(reservation);
        if(data.getSchedule() != null){
            Schedule schedule = data.getSchedule();
            schedule.setAvailable(0);
            scheduleRepository.save(schedule);
        }
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
    public List<ReservationDayDto> listByDay(String day) {
        List<Object[]> listReservations = new ArrayList<>();
        List<ReservationDayDto> dtos = new ArrayList<>();
        if(day != null) {
            LocalDate date = LocalDate.parse(day);
            listReservations = reservationRepository.findReservationsByDay(date);

            for (Object[] row : listReservations) {
                int reservationId = (Integer) row[0];
                String name = (String) row[1];
                String lastName = (String) row[2];
                String numberPhone = (String) row[3];
                LocalTime hour = (LocalTime) row[4];
                String fullName = name + " " + lastName;
                ReservationDayDto dto = new ReservationDayDto(reservationId, fullName , numberPhone, hour);
                dtos.add(dto);
            }

        }
            return dtos;

    }

    @Override
    public ReservationDto updateReservation(int id, int scheduleId) {
        ReservationDto data = new ReservationDto();
        try {
        LocalDateTime date = LocalDateTime.now();
        reservationRepository.updateReservation(scheduleId, id, date);
        data.setReservationId(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return data;
    }

    @Override
    public void deleteReservation(int reservationId) {

    }

}
