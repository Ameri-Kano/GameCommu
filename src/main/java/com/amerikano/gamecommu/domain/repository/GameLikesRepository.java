package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.GameLikes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLikesRepository extends JpaRepository<GameLikes, Long> {

  boolean existsByUserIdAndGameId(Long userId, Long gameId);

  Optional<GameLikes> findByUserIdAndGameId(Long userId, Long gameId);

}
