package com.riservi.gestion.app.contollers;


import com.riservi.gestion.app.entity.Customer;
import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import com.riservi.gestion.app.service.dtos.ReservationRequestDto;
import com.riservi.gestion.app.service.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseEntity<List<ReservationDto>> getAll(){
        List<ReservationDto> reservationList = reservationService.getAll();
    if(reservationList == null || reservationList.isEmpty()){
        return ResponseEntity.ok(Collections.emptyList());
    }
    return ResponseEntity.ok(reservationList);
    }

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
        if(dtoRequest != null && dtoRequest.getCustomerId() > 0 && dtoRequest.getScheduleId() > 0
              && dtoRequest.getReservationDate() != null) {
            ReservationDto reservation = reservationService.insertReservation(validateCustomerAndScheduler(dtoRequest));
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);

        }
        return ResponseEntity.badRequest().build();
    }

    private Reservation validateCustomerAndScheduler(ReservationRequestDto dtoRequest) {
       Customer customer = customerService.findById(dtoRequest.getCustomerId());
       Schedule schedule = scheduleService.findById(dtoRequest.getScheduleId());
        Reservation reservation = new Reservation();

        if (customer != null && schedule != null) {
            reservation.setCustomer(customer);
            reservation.setSchedule(schedule);
            reservation.setReservationDate(Util.convertToDate(dtoRequest.getReservationDate()));
        }
        return reservation;
    }
}
