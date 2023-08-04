package com.amerikano.gamecommu.service.review;

import com.amerikano.gamecommu.domain.dto.review.ReviewDto;
import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.entity.Review;
import com.amerikano.gamecommu.domain.entity.User;
import com.amerikano.gamecommu.domain.repository.GameRepository;
import com.amerikano.gamecommu.domain.repository.ReviewRepository;
import com.amerikano.gamecommu.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final GameRepository gameRepository;
  private final UserRepository userRepository;

  @Transactional
  public ReviewDto registerGameReview(Long userId, ReviewDto reviewDto) {

    User user = getUserEntity(userId);

    Game game = getGameEntity(reviewDto.getGameId());

    double rateValue = reviewDto.getRate().getValue();

    Review newReview = Review.builder()
        .user(user)
        .game(game)
        .title(reviewDto.getTitle())
        .text(reviewDto.getText())
        .rate(rateValue)
        .build();

    reviewRepository.save(newReview);

    setGameRate(game);

    return reviewDto;
  }

  @Transactional
  public ReviewDto modifyGameReview(Long userId, ReviewDto reviewDto) {

    User user = getUserEntity(userId);
    Game game = getGameEntity(reviewDto.getGameId());

    Review review = reviewRepository.findByUserAndGame(user, game)
        .orElseThrow(() -> new RuntimeException("해당하는 리뷰가 존재하지 않습니다."));

    review.setTitle(reviewDto.getTitle());
    review.setText(reviewDto.getText());
    review.setRate(reviewDto.getRate().getValue());

    setGameRate(game);

    return reviewDto;
  }

  @Transactional
  public String deleteGameReview(Long userId, Long gameId) {

    User user = getUserEntity(userId);
    Game game = getGameEntity(gameId);

    Review review = reviewRepository.findByUserAndGame(user, game)
        .orElseThrow(() -> new RuntimeException("해당하는 리뷰가 존재하지 않습니다."));

    reviewRepository.delete(review);

    setGameRate(game);

    return "리뷰 삭제가 완료되었습니다.";
  }

  private User getUserEntity(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("해당하는 유저가 존재하지 않습니다."));
  }

  private Game getGameEntity(Long gameId) {
    return gameRepository.findById(gameId)
        .orElseThrow(() -> new RuntimeException("해당하는 게임이 존재하지 않습니다."));
  }

  private void setGameRate(Game game) {
    double avgRate = reviewRepository.findAllByGame(game)
        .stream()
        .mapToDouble(Review::getRate)
        .average()
        .orElse(0.0);

    game.setRate(avgRate);
  }
}
