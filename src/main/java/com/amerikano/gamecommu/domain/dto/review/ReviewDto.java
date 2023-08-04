package com.amerikano.gamecommu.domain.dto.review;

import com.amerikano.gamecommu.domain.type.ReviewRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

  private Long gameId;
  private String title;
  private String text;
  private ReviewRate rate;

}
