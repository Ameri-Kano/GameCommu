package com.amerikano.gamecommu.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewRate {
  RATE_1(1),
  RATE_2(2),
  RATE_3(3),
  RATE_4(4),
  RATE_5(5);


  private final double value;
}
