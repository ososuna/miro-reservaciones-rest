package dev.ososuna.miro.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  private Long createdBy;

  @Column(name="updated_by")
  private Long updatedBy;

  @Column(name="created_date")
  @CreationTimestamp
  private LocalDate createdDate;

  @Column(name="updated_date")
  @UpdateTimestamp
  private LocalDate updatedDate;

  @Column(name="active")
  private boolean active = true;

}
