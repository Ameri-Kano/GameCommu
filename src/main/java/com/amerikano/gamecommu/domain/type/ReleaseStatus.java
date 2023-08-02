package com.amerikano.gamecommu.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 출시 상태 표현 상수
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ReleaseStatus {
  NOT_RELEASED("미출시"),
  PRE_RELEASED("사전 예약중"),
  RELEASED("출시"),
  SERVICE_END("서비스 종료"),
  DELETED("정보 삭제됨");


  private String description;
}
