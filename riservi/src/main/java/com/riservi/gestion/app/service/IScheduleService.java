package com.riservi.gestion.app.service;

import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.service.dtos.ScheduleDto;

public interface IScheduleService {

    Schedule findById(int scheduleId);

    Schedule findByAvailable(int available);

    Schedule findByDate(String date);

    void insertSchedule(ScheduleDto scheduleDto);

    Schedule updateSchedule(ScheduleDto scheduleDto);

    void deleteSchedule(int scheduleId);
}
