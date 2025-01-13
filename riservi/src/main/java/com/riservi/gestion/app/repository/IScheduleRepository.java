package com.riservi.gestion.app.repository;

import com.riservi.gestion.app.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {


    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.hour = :hour")
    Schedule findByDayHour(LocalDate date, LocalTime hour);
}
