package com.amerikano.gamecommu.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LikeStatus {

  LIKE("추천함"),
  DISLIKE("비추천함");

  private String description;
}
