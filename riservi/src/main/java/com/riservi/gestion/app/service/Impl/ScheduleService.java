package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.repository.IScheduleRepository;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Override
    public Schedule findById(int scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        return schedule.orElse(null);
    }

    @Override
    public Schedule findByAvailable(int available) {
        return null;
    }

    @Override
    public Schedule findByDate(String date) {
        return null;
    }

    @Override
    public void insertSchedule(ScheduleDto scheduleDto) {

    }

    @Override
    public Schedule updateSchedule(ScheduleDto scheduleDto) {
        return null;
    }

    @Override
    public void deleteSchedule(int scheduleId) {

    }
}
