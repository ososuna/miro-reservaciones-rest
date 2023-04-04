package dev.ososuna.miro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
  
}
