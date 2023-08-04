package com.amerikano.gamecommu.controller.review;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.amerikano.gamecommu.domain.dto.review.ReviewDto;
import com.amerikano.gamecommu.encryption.JwtAuthService;
import com.amerikano.gamecommu.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/games/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  private final JwtAuthService authService;

  @PostMapping
  ResponseEntity<ReviewDto> registerGameReview(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody ReviewDto reviewDto
  ) {
    return ResponseEntity.ok(reviewService.registerGameReview(
        authService.getIdFromToken(token), reviewDto
    ));
  }

  @PutMapping
  ResponseEntity<ReviewDto> modifyGameReview(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody ReviewDto reviewDto
  ) {
    return ResponseEntity.ok(reviewService.modifyGameReview(
        authService.getIdFromToken(token), reviewDto
    ));
  }

  @DeleteMapping
  ResponseEntity<String> deleteGameReview(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestParam(name = "gameid") Long gameId
  ) {
    return ResponseEntity.ok(reviewService.deleteGameReview(
        authService.getIdFromToken(token), gameId
    ));
  }
}
