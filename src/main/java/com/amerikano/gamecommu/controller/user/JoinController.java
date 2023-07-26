package com.amerikano.gamecommu.controller.user;

import com.amerikano.gamecommu.domain.dto.user.JoinForm;
import com.amerikano.gamecommu.service.user.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/join")
@RestController
@RequiredArgsConstructor
public class JoinController {

  private final JoinService joinService;

  @PostMapping
  public ResponseEntity<String> joinUser(@RequestBody JoinForm joinForm) {
    return ResponseEntity.ok(joinService.joinUser(joinForm));
  }
}
