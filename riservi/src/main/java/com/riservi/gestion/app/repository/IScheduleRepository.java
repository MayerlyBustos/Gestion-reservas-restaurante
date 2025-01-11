package com.riservi.gestion.app.repository;

import com.riservi.gestion.app.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {
}
