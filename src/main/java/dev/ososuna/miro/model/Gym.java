package dev.ososuna.miro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name="gym")
public class Gym {
 
  @JoinColumn(name="section_id", nullable=false)
  @OneToMany
  private Section section;

  @JsonBackReference(value="reservations")
  @OneToMany(mappedBy="gym")
  private List<Reservation> reservations;

}
