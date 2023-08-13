package com.amerikano.gamecommu.controller.likes;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.amerikano.gamecommu.domain.dto.likes.ReviewLikesDto;
import com.amerikano.gamecommu.encryption.JwtAuthService;
import com.amerikano.gamecommu.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/games/review/likes")
@RestController
@RequiredArgsConstructor
public class ReivewLikesController {

  private final LikesService likesService;
  private final JwtAuthService authService;

  @PostMapping
  ResponseEntity<ReviewLikesDto> registerGameLikes(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody ReviewLikesDto reviewLikesDto
  ) {
    return ResponseEntity.ok(likesService.registerReviewLikes(
        authService.getIdFromToken(token), reviewLikesDto
    ));
  }

  @DeleteMapping
  ResponseEntity<String> cancelGameLikes(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestParam Long reviewId
  ) {
    return ResponseEntity.ok(likesService.cancelReviewLikes(
        authService.getIdFromToken(token), reviewId
    ));
  }
}
