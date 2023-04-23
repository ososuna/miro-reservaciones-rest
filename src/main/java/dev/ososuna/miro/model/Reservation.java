package dev.ososuna.miro.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation")
public class Reservation extends AbstractModificationAttributesEntity {

  @Column(name="day", nullable=false)
  private LocalDate day;

  @Column(name="start_time", nullable=false)
  private LocalTime startTime;

  @Column(name="end_time", nullable=false)
  private LocalTime endTime;

  @JoinColumn(name="resident_id", nullable=false)
  @ManyToOne
  private Resident resident;

  @JoinColumn(name="gym_id", nullable=false)
  @ManyToOne
  private Gym gym;

}
