package dev.ososuna.miro.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractModificationAttributesEntity extends AbstractSimpleEntity {

  @Column(name="created_by")
  private UUID createdBy;

  @Column(name="updated_by")
  private UUID updatedBy;

  @Column(name="created_date")
  private LocalDate createdDate;

  @Column(name="updated_date")
  private LocalDate updatedDate;

  @Column(name="active")
  private boolean active;

}
