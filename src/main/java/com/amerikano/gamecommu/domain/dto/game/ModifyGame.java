package com.amerikano.gamecommu.domain.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ModifyGame {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RequestDto extends GameInfo.RequestDto {

    private Long id;
  }

}
