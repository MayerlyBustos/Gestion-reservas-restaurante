package com.riservi.gestion.app.service.Impl;

import com.riservi.gestion.app.entity.Schedule;
import com.riservi.gestion.app.repository.IScheduleRepository;
import com.riservi.gestion.app.service.IScheduleService;
import com.riservi.gestion.app.service.dtos.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    public List<Schedule> findByAvailable(String day) {
        if(day != null) {
            LocalDate date = LocalDate.parse(day);
            return scheduleRepository.findScheduleAvailable(date, 1);
        }
        return null;
    }

    @Override
    public Schedule findByDate(String date) {
        return null;
    }

    @Override
    public Schedule findByDayHour(LocalDate day, LocalTime time) {
        return scheduleRepository.findByDayHour(day, time);
    }

    @Override
    public ScheduleDto findByHour(String hour) {
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
