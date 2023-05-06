package dev.ososuna.miro.util;

import org.springframework.stereotype.Component;

import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Resident;
import dev.ososuna.miro.model.dto.ResidentDto;
import dev.ososuna.miro.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResidentUtil {

  private final ResidentRepository residentRepository;  

  public Resident getResidentByEmail(String email) throws NotFoundException {
    return residentRepository.findByEmailAndActiveTrue(email).orElseThrow(() -> new NotFoundException("El residente no existe"));
  }

  public Resident getResidentById(Long id) throws NotFoundException {
    return residentRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new NotFoundException("El residente no existe"));
  }

  public ResidentDto transformResidentToResidentDto(Resident resident) {
    var residentDto = ResidentDto.builder()
      .id(resident.getId().toString())
      .firstName(resident.getFirstName())
      .lastName(resident.getLastName())
      .email(resident.getEmail())
      .section(resident.getSection().getId().intValue())
      .role(resident.getRole().name())
      .build();
    return residentDto;
  }

}