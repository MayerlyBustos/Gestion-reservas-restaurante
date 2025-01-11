package com.riservi.gestion.app.contollers;


import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.service.ICustomerService;
import com.riservi.gestion.app.service.IReservationService;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
