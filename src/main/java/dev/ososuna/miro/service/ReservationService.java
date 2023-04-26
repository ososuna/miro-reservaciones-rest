package dev.ososuna.miro.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Reservation;
import dev.ososuna.miro.model.dto.AvailableHourDto;
import dev.ososuna.miro.model.dto.AvailableHoursResponseDto;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.repository.ReservationRepository;
import dev.ososuna.miro.util.ReservationUtil;
import dev.ososuna.miro.util.ResidentUtil;
import dev.ososuna.miro.util.SessionUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationUtil reservationUtil;
  private final ResidentUtil residentUtil;
  private final SessionUtil sessionUtil;
  private final ReservationRepository reservationRepository;

  public AvailableHoursResponseDto getAvailableHours(String date) throws BadRequestException {
    List<AvailableHourDto> availableHours = new ArrayList<>();
    var givenDate = LocalDate.parse(date);
    var currentDate = LocalDate.now();
    if (givenDate.isBefore(currentDate)) {
      throw new BadRequestException("You can't reserve a day before today");
    }
    var now = LocalTime.now();
    var start = LocalTime.of(6, 0);
    var end = LocalTime.of(23, 0);
    availableHours.addAll(
      Stream.iterate(start, d -> d.plusHours(1))
          .limit(ChronoUnit.HOURS.between(start, end))
          .filter(d -> !givenDate.equals(currentDate) || d.isAfter(now))
          .filter(d -> !reservationRepository.existsByDayAndStartTimeAndEndTime(givenDate, d, d.plusHours(1)))
          .map(d -> new AvailableHourDto(d.toString(), d.plusHours(1).toString()))
          .collect(Collectors.toList()));
    return AvailableHoursResponseDto.builder()
      .day(givenDate.toString())
      .availableHours(availableHours)
      .build();
  }

  public Reservation reserve(ReserveRequestDto request) throws NotFoundException, BadRequestException {
    var resident = residentUtil.getResidentById(sessionUtil.getLoggedUserId());
    reservationUtil.validateUniqueReservation(request);
    var reservation = Reservation.builder()
      .day(LocalDate.parse(request.getDay()))
      .startTime(LocalTime.parse(request.getStartTime()))
      .endTime(LocalTime.parse(request.getEndTime()))
      .resident(resident)
      .gym(resident.getSection().getGym())
      .build();
    sessionUtil.setCreatedBy(reservation);
    sessionUtil.setUpdatedBy(reservation);
    return reservationRepository.save(reservation);
  }
  
}
