package com.amerikano.gamecommu.controller.likes;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.amerikano.gamecommu.domain.dto.likes.GameLikesDto;
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

@RequestMapping("/games/likes")
@RestController
@RequiredArgsConstructor
public class GameLikesController {

  private final LikesService likesService;
  private final JwtAuthService authService;

  @PostMapping
  ResponseEntity<GameLikesDto> registerGameLikes(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody GameLikesDto gameLikesDto
      ) {
      return ResponseEntity.ok(likesService.registerGameLikes(
          authService.getIdFromToken(token), gameLikesDto
      ));
  }

  @DeleteMapping
  ResponseEntity<String> cancelGameLikes(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestParam Long gameId
  ) {
    return ResponseEntity.ok(likesService.cancelGameLikes(
        authService.getIdFromToken(token), gameId
    ));
  }
}
