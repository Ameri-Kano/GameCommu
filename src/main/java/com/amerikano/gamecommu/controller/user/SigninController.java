package com.amerikano.gamecommu.controller.user;

import com.amerikano.gamecommu.domain.dto.user.SigninForm;
import com.amerikano.gamecommu.domain.dto.user.SigninResultDto;
import com.amerikano.gamecommu.service.user.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/signin")
@RestController
@RequiredArgsConstructor
public class SigninController {

  private final SigninService signinService;

  @PostMapping
  public ResponseEntity<SigninResultDto> signinUser(@RequestBody SigninForm form) {
    SigninResultDto resultDto = signinService.signinUser(form);

    if (resultDto.getToken() == null) {
      return ResponseEntity.badRequest()
          .body(resultDto);
    }

    return ResponseEntity.ok(resultDto);
  }
}
