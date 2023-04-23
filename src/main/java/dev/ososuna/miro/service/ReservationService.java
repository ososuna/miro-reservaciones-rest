package dev.ososuna.miro.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Reservation;
import dev.ososuna.miro.model.dto.AvailableHourDto;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.repository.ReservationRepository;
import dev.ososuna.miro.util.ResidentUtil;
import dev.ososuna.miro.util.SessionUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ResidentUtil residentUtil;
  private final SessionUtil sessionUtil;
  private final ReservationRepository reservationRepository;

  public List<AvailableHourDto> getAvailableHours() {
    return null;
  }

  public Reservation reserve(ReserveRequestDto request) throws NotFoundException {
    var resident = residentUtil.getResidentById(sessionUtil.getLoggedUserId());
    var reservation = Reservation.builder()
      .day(LocalDate.parse(request.getDay()))
      .startTime(LocalTime.parse(request.getStartTime()))
      .endTime(LocalTime.parse(request.getEndTime()))
      .resident(resident)
      .gym(resident.getSection().getGym())
      .build();
    sessionUtil.setCreatedBy(resident);
    return reservationRepository.save(reservation);
  }
  
}
