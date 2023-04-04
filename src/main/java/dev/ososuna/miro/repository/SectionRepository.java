package dev.ososuna.miro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

  Optional<Section> findByIdAndActiveTrue(Long id);

}
