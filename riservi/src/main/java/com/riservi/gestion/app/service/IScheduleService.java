package com.riservi.gestion.app.service;

import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.dtos.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IScheduleService {

    Schedule findById(int scheduleId);

    Schedule findByAvailable(int available);

    Schedule findByDate(String date);

    Schedule findByDayHour(LocalDate day, LocalTime hour);

    ScheduleDto findByHour(String hour);

    void insertSchedule(ScheduleDto scheduleDto);

    Schedule updateSchedule(ScheduleDto scheduleDto);

    void deleteSchedule(int scheduleId);
}
