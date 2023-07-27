package com.amerikano.gamecommu.controller.gameinfo;

import com.amerikano.gamecommu.domain.dto.game.GameInfo.RequestDto;
import com.amerikano.gamecommu.domain.dto.game.GameInfo.ResponseDto;
import com.amerikano.gamecommu.domain.dto.game.ModifyGame;
import com.amerikano.gamecommu.service.gameinfo.GameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/games")
@RestController
@RequiredArgsConstructor
public class GameInfoController {

  private final GameInfoService gameInfoService;

  @PostMapping("/register")
  ResponseEntity<ResponseDto> registerGameInfo(@RequestBody RequestDto requestDto) {
    return ResponseEntity.ok(gameInfoService.registerGameInfo(requestDto));
  }

  @PutMapping("/modify")
  ResponseEntity<ResponseDto> modifyGameInfo(@RequestBody ModifyGame.RequestDto requestDto) {
    return ResponseEntity.ok(gameInfoService.modifyGameInfo(requestDto));
  }

  @PutMapping("/delete")
  ResponseEntity<String> deleteGameInfo(@RequestParam Long id) {
    return ResponseEntity.ok(gameInfoService.deleteGameInfo(id));
  }
}