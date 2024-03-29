package dev.ososuna.miro.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractSimpleEntity {
  @Id  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(
    name="id",
    updatable=false,
    nullable=false,
    unique=true
  )
  private Long id;
}
