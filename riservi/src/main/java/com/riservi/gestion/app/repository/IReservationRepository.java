package com.riservi.gestion.app.repository;

import com.riservi.gestion.app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.customer.customerId = :customerId")
    List<Reservation> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT r FROM Reservation r WHERE r.schedule.scheduleId = :scheduleId")
    Reservation findByScheduleId(@Param("scheduleId") Integer scheduleId);
}
