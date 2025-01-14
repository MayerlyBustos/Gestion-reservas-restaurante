package com.riservi.gestion.app.contollers;


import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.AvailableDto;
import com.riservi.gestion.app.service.dtos.ReservationDayDto;
import com.riservi.gestion.app.service.dtos.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("riservi")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

//    @GetMapping("schedule/available")
//    public ResponseEntity<String> getAll(){
//        List<ReservationDto> schedule = scheduleService.getCla;
//        if(reservationList == null || reservationList.isEmpty()){
//            return ResponseEntity.ok(Collections.emptyList());
//        }
//        return ResponseEntity.ok(reservationList);
//    }


    @GetMapping("/listByDay")
    public ResponseEntity<List<AvailableDto>> listByDay(@RequestParam("date") String date){
        List<Schedule> available = scheduleService.findByAvailable(date);
        List<AvailableDto> response = getRespose(available);
        if(response.isEmpty()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    private List<AvailableDto> getRespose(List<Schedule> availables) {
        List<AvailableDto> responses = new ArrayList<>();
        if(!availables.isEmpty()){
            for (Schedule available :  availables){
                AvailableDto data = new AvailableDto();
                data.setHour(available.getHour());
                responses.add(data);
            }
        }
        return  responses;

    }
}
