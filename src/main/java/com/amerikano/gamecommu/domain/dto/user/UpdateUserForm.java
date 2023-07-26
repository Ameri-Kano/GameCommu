package com.amerikano.gamecommu.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

  private String email;
  private String name;
  private String birth;
  private String phone;

}
