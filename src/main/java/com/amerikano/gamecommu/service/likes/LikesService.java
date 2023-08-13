package com.amerikano.gamecommu.service.likes;

import static com.amerikano.gamecommu.domain.type.LikeStatus.LIKE;

import com.amerikano.gamecommu.domain.dto.likes.GameLikesDto;
import com.amerikano.gamecommu.domain.dto.likes.ReviewLikesDto;
import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.entity.GameLikes;
import com.amerikano.gamecommu.domain.entity.Review;
import com.amerikano.gamecommu.domain.entity.ReviewLikes;
import com.amerikano.gamecommu.domain.repository.GameLikesRepository;
import com.amerikano.gamecommu.domain.repository.GameRepository;
import com.amerikano.gamecommu.domain.repository.ReviewLikesRepository;
import com.amerikano.gamecommu.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

  private final GameLikesRepository gameLikesRepository;
  private final ReviewLikesRepository reviewLikesRepository;
  private final GameRepository gameRepository;
  private final ReviewRepository reviewRepository;

  @Transactional
  public GameLikesDto registerGameLikes(Long userId, GameLikesDto gameLikesDto) {
    Game game = gameRepository.findById(gameLikesDto.getGameId())
        .orElseThrow(() -> new RuntimeException("해당하는 게임이 존재하지 않습니다."));

    if (gameLikesRepository.existsByUserIdAndGameId(userId, gameLikesDto.getGameId())) {
      throw new RuntimeException("이미 해당 게임에 대해 추천/비추천을 남겼습니다.");
    }

    GameLikes newGameLikes = GameLikes.builder()
        .userId(userId)
        .gameId(gameLikesDto.getGameId())
        .likeStatus(gameLikesDto.getLikeStatus())
        .build();

    gameLikesRepository.save(newGameLikes);

    if (gameLikesDto.getLikeStatus() == LIKE) {
      game.setLikes(game.getLikes() + 1);
    } else {
      game.setDislikes(game.getDislikes() + 1);
    }

    return gameLikesDto;
  }

  @Transactional
  public String cancelGameLikes(Long userId, Long gameId) {
    Game game = gameRepository.findById(gameId)
        .orElseThrow(() -> new RuntimeException("해당하는 게임이 존재하지 않습니다."));

    GameLikes gameLikes = gameLikesRepository.findByUserIdAndGameId(userId, gameId)
        .orElseThrow(() -> new RuntimeException("해당하는 추천 정보가 존재하지 않습니다."));

    if (gameLikes.getLikeStatus() == LIKE) {
      game.setLikes(game.getLikes() - 1);
    } else {
      game.setDislikes(game.getDislikes() - 1);
    }

    gameLikesRepository.delete(gameLikes);

    return game.getTitle() + " 게임의 추천/비추천 취소가 완료되었습니다.";
  }

  @Transactional
  public ReviewLikesDto registerReviewLikes(Long userId, ReviewLikesDto reviewLikesDto) {
    Review review = reviewRepository.findById(reviewLikesDto.getReviewId())
        .orElseThrow(() -> new RuntimeException("해당하는 리뷰가 존재하지 않습니다."));

    if (reviewLikesRepository.existsByUserIdAndReviewId(userId, reviewLikesDto.getReviewId())) {
      throw new RuntimeException("이미 해당 리뷰에 대해 추천/비추천을 남겼습니다.");
    }

    ReviewLikes newReviewLikes = ReviewLikes.builder()
        .userId(userId)
        .reviewId(reviewLikesDto.getReviewId())
        .likeStatus(reviewLikesDto.getLikeStatus())
        .build();

    reviewLikesRepository.save(newReviewLikes);

    if (reviewLikesDto.getLikeStatus() == LIKE) {
      review.setLikes(review.getLikes() + 1);
    } else {
      review.setDislikes(review.getDislikes() + 1);
    }

    return reviewLikesDto;
  }

  @Transactional
  public String cancelReviewLikes(Long userId, Long reviewId) {
    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new RuntimeException("해당하는 리뷰가 존재하지 않습니다."));

    ReviewLikes reviewLikes = reviewLikesRepository.findByUserIdAndReviewId(userId, reviewId)
        .orElseThrow(() -> new RuntimeException("해당하는 추천 정보가 존재하지 않습니다."));

    if (reviewLikes.getLikeStatus() == LIKE) {
      review.setLikes(review.getLikes() - 1);
    } else {
      review.setDislikes(review.getDislikes() - 1);
    }

    reviewLikesRepository.delete(reviewLikes);

    return "리뷰의 추천/비추천 취소가 완료되었습니다.";
  }
}
