package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

  boolean existsByTitle(String title);
}
