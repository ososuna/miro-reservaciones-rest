package dev.ososuna.miro.util;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationUtil {
  
  private final ReservationRepository reservationRepository;

  public void validateUniqueReservation(ReserveRequestDto reservation) throws BadRequestException {
    var exists = reservationRepository.existsByDayAndStartTimeAndEndTime(
      LocalDate.parse(reservation.getDay()),
      LocalTime.parse(reservation.getStartTime()),
      LocalTime.parse(reservation.getEndTime()));
    if (exists) {
      throw new BadRequestException("Reservation already exists");
    } 
  }

}
