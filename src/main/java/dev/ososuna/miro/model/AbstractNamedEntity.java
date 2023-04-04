package dev.ososuna.miro.model;

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
public abstract class AbstractNamedEntity extends AbstractModificationAttributesEntity {
  @Column
  private String name;
}
