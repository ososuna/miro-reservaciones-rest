package dev.ososuna.miro.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  boolean existsByDayAndStartTimeAndEndTime(LocalDate day, LocalTime startTime, LocalTime endTime);
  
}
