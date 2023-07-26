package com.amerikano.gamecommu.domain.entity;

import com.amerikano.gamecommu.domain.type.LikeStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "like_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameLikes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private Long gameId;

  @Enumerated(value = EnumType.STRING)
  private LikeStatus likeStatus;

}
