package com.amerikano.gamecommu.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResultDto {

  private String message;
  private String token;

}
