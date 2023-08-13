package com.amerikano.gamecommu.domain.dto.likes;

import com.amerikano.gamecommu.domain.type.LikeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikesDto {

  private LikeStatus likeStatus;

}
