package dev.ososuna.miro.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Reservation;
import dev.ososuna.miro.model.Resident;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  boolean existsByDayAndStartTimeAndEndTime(LocalDate day, LocalTime startTime, LocalTime endTime);

  List<Reservation> findByResident(Resident resident);
  
}
