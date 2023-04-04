package dev.ososuna.miro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  
}
