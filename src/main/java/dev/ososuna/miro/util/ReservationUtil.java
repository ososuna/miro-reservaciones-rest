package dev.ososuna.miro.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.model.dto.AvailableHourDto;
import dev.ososuna.miro.model.dto.AvailableHoursResponseDto;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationUtil {
  
  private final ReservationRepository reservationRepository;

  public AvailableHoursResponseDto getTotalAvailableHoursForToday() {
    List<AvailableHourDto> availableHours = new ArrayList<>();
    var now = LocalTime.now();
    var start = LocalTime.of(6, 0);
    var end = LocalTime.of(23, 0);
    while (start.isBefore(end)) {
      if (start.isAfter(now)) {
        availableHours.add(new AvailableHourDto(start.toString(), start.plusHours(1).toString()));
      }
      start = start.plusHours(1);
    }
    return AvailableHoursResponseDto.builder()
      .day(LocalDate.now().toString())
      .availableHours(availableHours)
      .build();
  }
  
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
