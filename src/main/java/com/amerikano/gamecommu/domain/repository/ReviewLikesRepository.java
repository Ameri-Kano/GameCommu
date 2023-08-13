package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.ReviewLikes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes, Long> {

  boolean existsByUserIdAndReviewId(Long userId, Long reviewId);

  Optional<ReviewLikes> findByUserIdAndReviewId(Long userId, Long reviewId);

}
