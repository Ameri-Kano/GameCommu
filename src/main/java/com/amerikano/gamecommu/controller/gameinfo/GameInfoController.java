package com.amerikano.gamecommu.controller.gameinfo;

import com.amerikano.gamecommu.domain.dto.game.RegisterGame.RequestDto;
import com.amerikano.gamecommu.service.gameinfo.GameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/games")
@RestController
@RequiredArgsConstructor
public class GameInfoController {

  private final GameInfoService gameInfoService;

  @PostMapping("/register")
  ResponseEntity<?> registerGameInfo(@RequestBody RequestDto requestDto) {
    return ResponseEntity.ok(gameInfoService.registerGameInfo(requestDto));
  }

}