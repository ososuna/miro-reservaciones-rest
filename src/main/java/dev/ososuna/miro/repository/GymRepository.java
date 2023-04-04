package dev.ososuna.miro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ososuna.miro.model.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
  
}
