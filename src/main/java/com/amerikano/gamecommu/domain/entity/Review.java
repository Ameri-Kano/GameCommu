package com.amerikano.gamecommu.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  private String title;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String text;

  @Column(columnDefinition = "double default 0.0")
  private double rate;

  @Column(name = "like_count", columnDefinition = "int default 0")
  private int likes;
  @Column(name = "dislike_count", columnDefinition = "int default 0")
  private int dislikes;

}
