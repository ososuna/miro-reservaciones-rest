package dev.ososuna.miro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private int section;
  private String role;
}
