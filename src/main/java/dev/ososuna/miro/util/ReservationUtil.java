package dev.ososuna.miro.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Reservation;
import dev.ososuna.miro.model.dto.ReservationDto;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationUtil {
  
  private final ReservationRepository reservationRepository;
  private final ResidentUtil residentUtil;

  public void validateUniqueReservation(ReserveRequestDto reservation) throws BadRequestException {
    var exists = reservationRepository.existsByDayAndStartTimeAndEndTime(
      LocalDate.parse(reservation.getDay()),
      LocalTime.parse(reservation.getStartTime()),
      LocalTime.parse(reservation.getEndTime()));
    if (exists) {
      throw new BadRequestException("Reservation already exists");
    } 
  }

  public List<Reservation> getReservationsByResidentId(Long residentId) throws NotFoundException {
    var resident = residentUtil.getResidentById(residentId);
    return reservationRepository.findByResident(resident);
  }

  public ReservationDto transformReservationToDto(Reservation reservation) {
    return ReservationDto.builder()
      .day(reservation.getDay().toString())
      .startTime(reservation.getStartTime().toString())
      .endTime(reservation.getEndTime().toString())
      .build();
  }
}
