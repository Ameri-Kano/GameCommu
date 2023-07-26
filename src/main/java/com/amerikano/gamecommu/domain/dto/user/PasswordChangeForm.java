package com.amerikano.gamecommu.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeForm {

  private String oldPassword;
  private String newPassword;

}
