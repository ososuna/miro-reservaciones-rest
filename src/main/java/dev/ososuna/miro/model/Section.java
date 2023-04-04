package dev.ososuna.miro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity
@Table(name="section")
public class Section extends AbstractNamedEntity {

  @JsonBackReference(value="gym")
  @OneToOne(mappedBy="section")
  private Gym gym;

  @JsonBackReference(value="residents")
  @OneToMany(mappedBy="section")
  private List<Resident> residents;

}
