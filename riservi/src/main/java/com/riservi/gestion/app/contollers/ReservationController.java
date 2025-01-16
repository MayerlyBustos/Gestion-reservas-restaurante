package com.riservi.gestion.app.contollers;


import com.riservi.gestion.app.contollers.dtos.MessageDto;
import com.riservi.gestion.app.contollers.dtos.ResponseDTO;
import com.riservi.gestion.app.contollers.dtos.UpdateReservationRequest;
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

import javax.transaction.Transactional;
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


    @GetMapping("/list")
    public ResponseEntity<List<ReservationDto>> getAll(){
        List < ReservationDto > reservationList = reservationService.getAll();
        if (reservationList == null || reservationList.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(reservationList);
    }
    @GetMapping("/{id}")
    public ResponseEntity < ReservationDto > getById(@PathVariable("id") Integer id){
        ReservationDto reservationDto = reservationService.findById(id);
        if (reservationDto != null) {
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity < List < ReservationDto >> getByCustomerId(@PathVariable("customerId") Integer customerId){
        List < ReservationDto > reservationDto = reservationService.findByCustomerId(customerId);
        if (reservationDto != null) {
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity < ReservationDto > getByScheduleId(@PathVariable("scheduleId") Integer scheduleId){
        ReservationDto reservationDto = reservationService.findByScheduleId(scheduleId);
        if (reservationDto != null) {
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity <MessageDto> saveReservation(@RequestBody ReservationRequestDto dtoRequest){
        if (dtoRequest != null && dtoRequest.getName() != null && dtoRequest.getEmail() != null
                && dtoRequest.getDate() != null && dtoRequest.getHour() != null) {
            ReservationDto reservation = reservationService.insertReservation(validateCustomerAndScheduler(dtoRequest));
            if (reservation != null) {
                return ResponseEntity.status(HttpStatus.CREATED).
                        body(new MessageDto(Constantes.CREATED, Constantes.CREATED_CODE, reservation));
            }
        }
        return ResponseEntity.badRequest().body(new MessageDto(Constantes.BAD_REQUEST,
                Constantes.NOT_AVAILABLE, null));
    }

    @GetMapping("/by-date")
    public ResponseEntity < List < ReservationDayDto >> getReservationsByDate(@RequestParam("day") String day) {
        List < ReservationDayDto > reservationDayDto = new ArrayList<>();
        List < ReservationDayDto > results = reservationService.listByDay(day);
        if (!results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(results);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
    }

    @Transactional
    @PutMapping("/update/{reservationId}")
    public ResponseEntity<MessageDto> updateReservation(@PathVariable int reservationId,
                                                        @RequestBody UpdateReservationRequest request) {

        ReservationDto result = reservationService.findById(reservationId);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageDto(Constantes.NOT_FOUND, Constantes.NOT_LIST, result));
        }
       Schedule schedule =  scheduleService.findById(request.getScheduleId());
        if(schedule == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageDto(Constantes.NOT_FOUND, Constantes.SCHEDULE_ERROR, result));
        }

        if (schedule.getAvailable() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(Constantes.BAD_REQUEST,
                    Constantes.NOT_AVAILABLE, null));
        }

        result.setSchedule(Util.mappearScheduleDto(schedule));
        schedule.setAvailable(0);

        int idCustomer = result.getCustomer().getCustomerId();
        Customer customer = customerService.findById(idCustomer);
        if(customer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageDto(Constantes.NOT_FOUND, Constantes.CUSTOMER_ERROR, result));
        }
        result.setCustomer(Util.mappearCustomerDto(customer));

        reservationService.insertReservation(Util.mapperEntity(result));
        //scheduleService
        return ResponseEntity.ok(new MessageDto(Constantes.UPDATE, Constantes.SUCCESS_CODE, result));


    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity < ResponseDTO > deleteReservation(@PathVariable("id") Integer id){
        boolean removed = reservationService.deleteReservation(id);
        if (removed) {
            return ResponseEntity.ok(new ResponseDTO(Constantes.SUCCESS_CODE, Constantes.DELETE));
        }
        return ResponseEntity.badRequest().body(new ResponseDTO(Constantes.BAD_REQUEST, Constantes.DEL_ERROR));
    }
    private Reservation validateCustomerAndScheduler(ReservationRequestDto dtoRequest) {
        Customer customer = customerService.findByEmail(dtoRequest.getEmail());
        Schedule schedule = scheduleService.findByDayHour(dtoRequest.getDate(), dtoRequest.getHour());
        customer = verifyExistCustomer(customer, dtoRequest);
        return llenarReservation(customer, schedule);
    }

    private Customer verifyExistCustomer(Customer customer, ReservationRequestDto dtoRequest) {
        CustomerDto newCustomer = new CustomerDto();
        if (customer == null) {
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
