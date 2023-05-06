package dev.ososuna.miro.util;

import org.springframework.stereotype.Component;

import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Section;
import dev.ososuna.miro.repository.SectionRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SectionUtil {
  
  private final SectionRepository sectionRepository;

  public Section getSectionById(int id) throws NotFoundException {
    return sectionRepository.findByIdAndActiveTrue(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("La sección no existe"));
  }

}
