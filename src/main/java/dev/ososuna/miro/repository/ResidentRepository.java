package dev.ososuna.miro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

  Optional<Resident> findByEmailAndActiveTrue(String email);

}
