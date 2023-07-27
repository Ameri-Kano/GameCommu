package com.amerikano.gamecommu.domain.entity;

import com.amerikano.gamecommu.domain.type.ReleaseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
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
public class Game extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany
  private List<Genre> genres;

  private String title;

  private String developer;

  @Column(columnDefinition = "double default 0.0")
  private double rate;

  @Column(name = "like_count", columnDefinition = "int default 0")
  private int likes;
  @Column(name = "dislike_count", columnDefinition = "int default 0")
  private int dislikes;

  @Column(name = "released_datetime")
  private LocalDateTime releasedDateTime;

  @Enumerated(value = EnumType.STRING)
  private ReleaseStatus releaseStatus;

}
