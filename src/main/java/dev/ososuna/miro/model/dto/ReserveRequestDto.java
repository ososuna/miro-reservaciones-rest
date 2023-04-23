package dev.ososuna.miro.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequestDto {
  private LocalDate day;
  private LocalTime startTime;
  private LocalTime endTime;
}
