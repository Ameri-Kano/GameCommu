package com.amerikano.gamecommu.domain.dto.user;

import java.time.LocalDate;
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
public class JoinForm {

  private String email;
  private String password;
  private String name;
  private LocalDate birth;
  private String phone;

}
