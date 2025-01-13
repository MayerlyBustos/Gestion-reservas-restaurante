package com.riservi.gestion.app.repository;

import com.riservi.gestion.app.entity.Reservation;
import com.riservi.gestion.app.service.dtos.ReservationDayDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.customer.customerId = :customerId")
    List<Reservation> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT r FROM Reservation r WHERE r.schedule.scheduleId = :scheduleId")
    Reservation findByScheduleId(@Param("scheduleId") Integer scheduleId);

    @Query("SELECT r.reservationId, c.name, c.lastName, c.numberPhone, s.hour FROM Reservation r INNER JOIN r.customer c INNER JOIN r.schedule s WHERE s.date = :day")
    List<Object[]> findReservationsByDay(@Param("day") LocalDate day);

    @Modifying
    @Query("UPDATE Reservation r SET r.schedule.scheduleId = :scheduleId, r.reservationDate = :reservationDate WHERE r.reservationId = :reservationId AND EXISTS (SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId AND s.available = 1)")
    void updateReservation(@Param("scheduleId") int scheduleId, @Param("reservationId") int reservationId, @Param("reservationDate") LocalDateTime reservationDate);

}
