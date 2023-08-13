package com.amerikano.gamecommu.domain.dto.likes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikesDto extends LikesDto {

  private Long reviewId;

}
