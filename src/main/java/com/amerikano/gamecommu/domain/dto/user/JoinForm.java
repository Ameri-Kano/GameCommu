package com.amerikano.gamecommu.domain.dto.user;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JoinForm {

  private String email;
  private String password;
  private String name;
  private LocalDate birth;
  private String phone;

}
