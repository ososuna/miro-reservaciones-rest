package dev.ososuna.miro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Reservation;
import dev.ososuna.miro.model.dto.AvailableHourDto;
import dev.ososuna.miro.model.dto.ReserveRequestDto;
import dev.ososuna.miro.service.ReservationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<Reservation> reserve(@RequestBody ReserveRequestDto request) throws NotFoundException, BadRequestException {
    return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.reserve(request));
  }

  @GetMapping("/available-hours")
  public ResponseEntity<List<AvailableHourDto>> getAvailableHours() {
    return ResponseEntity.ok(reservationService.getAvailableHours());
  }
}
