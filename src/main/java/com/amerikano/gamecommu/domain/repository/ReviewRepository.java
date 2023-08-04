package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.entity.Review;
import com.amerikano.gamecommu.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  Optional<Review> findByUserAndGame(User user, Game game);
}
