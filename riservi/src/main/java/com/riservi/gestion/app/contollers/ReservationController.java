package com.riservi.gestion.app.contollers;


import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.*;
import com.riservi.gestion.app.service.utils.Constantes;
import com.riservi.gestion.app.service.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("riservi")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IScheduleService scheduleService;
    

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getById(@PathVariable("id") Integer id){
        ReservationDto reservationDto = reservationService.findById(id);
        if(reservationDto != null){
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationDto>> getByCustomerId(@PathVariable("customerId") Integer customerId){
        List<ReservationDto> reservationDto = reservationService.findByCustomerId(customerId);
        if(reservationDto != null){
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<ReservationDto> getByScheduleId(@PathVariable("scheduleId") Integer scheduleId){
        ReservationDto reservationDto = reservationService.findByScheduleId(scheduleId);
        if(reservationDto != null){
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationRequestDto dtoRequest){
        ReservationDto message = new ReservationDto();
        if(dtoRequest != null && dtoRequest.getName() != null && dtoRequest.getEmail() != null
                && dtoRequest.getDate() != null && dtoRequest.getHour() != null) {
            ReservationDto reservation = reservationService.insertReservation(validateCustomerAndScheduler(dtoRequest));
            if(reservation != null){
                getMessageRtaExito(Constantes.CREATED, Constantes.CREATED_CODE, reservation);
                return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
            }

            message = getMessageError(Constantes.NOT_AVAILABLE, Constantes.BAD_REQUEST);
        }
        return ResponseEntity.badRequest().body(message);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<ReservationDayDto>> getReservationsByDate(@RequestParam ("day") String day) {
        List<ReservationDayDto> reservationDayDto = new ArrayList<>();
        List<ReservationDayDto> results = reservationService.listByDay(day);
        if(!results.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(results);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDao> updateReservation(@PathVariable("id") int id, @RequestBody int scheduleId){

        MessageDao messageRtaDao = null;
        if(id > 0){
            String result = reservationService.updateReservation(id, scheduleId);
            if(result != null){
                messageRtaDao = new MessageDao(Constantes.UPDATE, Constantes.SUCCESS_CODE);
                return ResponseEntity.ok(messageRtaDao);
            }
        }
        messageRtaDao = new MessageDao(Constantes.UPDATE_ERROR, Constantes.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRtaDao);
    }

    private ReservationDto getMessageError(String message, String code) {
        ReservationDto rta = new ReservationDto();
        rta.setMessage(message);
        rta.setCode(code);
        return rta;
    }

    private void getMessageRtaExito(String message, String code, ReservationDto reservationDto) {
        reservationDto.setMessage(message);
        reservationDto.setCode(code);
    }

    private Reservation validateCustomerAndScheduler(ReservationRequestDto dtoRequest) {
        Customer customer = customerService.findByEmail(dtoRequest.getEmail());
        Schedule schedule = scheduleService.findByDayHour(dtoRequest.getDate(), dtoRequest.getHour());
        customer = verifyExistCustomer(customer, dtoRequest);
        return  llenarReservation(customer, schedule);
    }

    private Customer verifyExistCustomer(Customer customer, ReservationRequestDto dtoRequest) {
        CustomerDto newCustomer = new CustomerDto();
        if(customer == null){
            newCustomer.setName(dtoRequest.getName());
            newCustomer.setLastName(dtoRequest.getLastName());
            newCustomer.setEmail(dtoRequest.getEmail());
            newCustomer.setNumberPhone(dtoRequest.getNumberPhone());
            return customerService.saveCustomer(newCustomer);
        }
        return customer;
    }

    private Reservation llenarReservation(Customer customer, Schedule schedule) {
        Reservation reservation = new Reservation();
        LocalDateTime now = LocalDateTime.now();
        boolean isAvailable = isScheduleAvailable(schedule);
        if (customer != null && schedule.getScheduleId() > 0 && isAvailable) {
            reservation.setCustomer(customer);
            reservation.setSchedule(schedule);
            reservation.setReservationDate(Util.convertToDate(now));
        } else {
            return null;
        }
        return reservation;
    }

    private boolean isScheduleAvailable(Schedule schedule) {
        boolean itIs = false;
        if (schedule != null && schedule.getScheduleId() > 0 && schedule.getAvailable() == 1) {
            return true;
        }
        return itIs;
    }

}
