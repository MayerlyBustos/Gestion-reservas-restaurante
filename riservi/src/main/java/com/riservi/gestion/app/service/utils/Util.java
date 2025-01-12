package com.riservi.gestion.app.service.utils;

import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.dtos.CustomerDto;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import com.riservi.gestion.app.service.dtos.ScheduleDto;

import java.sql.Time;
import java.time.*;
import java.util.*;

public class Util {

    public static List<ReservationDto> mappearReservation(List<Reservation> reservations){

        List<ReservationDto> reservationDto = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto dto = new ReservationDto();
            dto.setReservationId(reservation.getReservationId());
            dto.setCustomer(mappearCustomerDto(reservation.getCustomer()));
            dto.setSchedule(mappearScheduleDto(reservation.getSchedule()));
            dto.setReservationDate(convertDateToLocalDate(reservation.getReservationDate()));
            reservationDto.add(dto);
        }

        return reservationDto;

    }

    public static Reservation mapperEntity(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        if(reservationDto != null){
            reservation.setCustomer(mappearCustomer(reservationDto.getCustomer()));
            reservation.setSchedule(mappearSchedule(reservationDto.getSchedule()));
            reservation.setReservationDate(convertToDate(reservationDto.getReservationDate()));
        }
        return reservation;
    }
    public static ReservationDto mappearReservationDto(Reservation reservation){
        ReservationDto dto = new ReservationDto();
        if(reservation != null){
            dto.setReservationId(reservation.getReservationId());
            dto.setCustomer(mappearCustomerDto(reservation.getCustomer()));
            dto.setSchedule(mappearScheduleDto(reservation.getSchedule()));
            dto.setReservationDate(convertDateToLocalDate(reservation.getReservationDate()));
        }

        return dto;
    }


    public static CustomerDto mappearCustomerDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        if(customer != null){
            dto.setCustomerId(customer.getCustomerId());
            dto.setName(customer.getName());
            dto.setLastName(customer.getLastName());
            dto.setEmail(customer.getEmail());
            dto.setNumberPhone(customer.getNumberPhone());
        }
        return dto;
    }

    public static Customer mappearCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        if(customerDto != null){
            customer.setCustomerId(customer.getCustomerId());
            customer.setName(customerDto.getName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setNumberPhone(customerDto.getNumberPhone());
        }
        return customer;
    }
    public static ScheduleDto mappearScheduleDto(Schedule schedule){
        ScheduleDto dto = new ScheduleDto();
        if (schedule != null) {
            dto.setScheduleId(schedule.getScheduleId());
            dto.setDate(schedule.getDate());
            dto.setHour(convertToLocaTime(schedule.getHour()));
            dto.setAvailable(schedule.getAvailable());
        }
        return dto;
    }

    public static Schedule mappearSchedule(ScheduleDto scheduleDto){
        Schedule schedule = new Schedule();
        if (scheduleDto != null) {
            schedule.setScheduleId(scheduleDto.getScheduleId());
            schedule.setDate(scheduleDto.getDate());
            schedule.setHour(convertToLocaTime(scheduleDto.getHour()));
            schedule.setAvailable(scheduleDto.getAvailable());
        }
        return schedule;
    }

    public static  ReservationDto mappearReservation(Reservation optionalReservation){
        ReservationDto dataDto = new ReservationDto();
        if(optionalReservation != null){
            dataDto.setReservationId(optionalReservation.getReservationId());
            dataDto.setCustomer(mappearCustomerDto(optionalReservation.getCustomer()));
            dataDto.setSchedule(mappearScheduleDto(optionalReservation.getSchedule()));
            dataDto.setReservationDate(convertDateToLocalDate(optionalReservation.getReservationDate()));
        }

        return dataDto;
    }
    public static LocalDateTime convertDateToLocalDate(Date date) {
        if (date != null) {
            Instant instant = date.toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }
    public static  Date convertLocalTimeToDate(LocalTime localTime) {
        if (localTime != null) {
            return Date.from(localTime.atDate(LocalDate.now())
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }
        return null;
    }

    public static Date convertToDate(LocalDateTime dateReservation) {

        Date date = null;
        if (dateReservation != null) {
            date = Date.from(dateReservation.atZone(ZoneId.systemDefault()).toInstant());
        }
        return date;
    }

    private static Time convertToLocaTime(Date dateReservation) {

        Time time = null;
        if (dateReservation != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateReservation);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            time = new Time(hour, minute, second);
        }
        return time;
    }
}
