package com.amerikano.gamecommu.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게임 장르 표현 상수
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Genres {
  ACTION("액션"),
  ADVENTURE("어드밴처"),
  MUSIC("음악");

  private String genreName;
}
