package com.amerikano.gamecommu.domain.entity;

import com.amerikano.gamecommu.domain.type.ReleaseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  private Double rate;

  @Column(name = "like_count")
  private Integer likes;
  @Column(name = "dislike_count")
  private Integer dislikes;

  private LocalDateTime releasedDate;

  private ReleaseStatus releaseStatus;

}
