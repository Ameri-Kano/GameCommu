package com.amerikano.gamecommu.domain.dto.game;

import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.type.ReleaseStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GameInfo {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RequestDto {

    private String title;

    private List<Long> genres;

    private String developer;

    private LocalDateTime releasedDateTime;

    private ReleaseStatus releaseStatus;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ResponseDto {

    private String title;

    private List<String> genres;

    private String developer;

    private LocalDateTime releasedDateTime;

    private ReleaseStatus releaseStatus;

    public static ResponseDto from(Game game, List<String> genreStrings) {
      return ResponseDto.builder()
          .title(game.getTitle())
          .genres(genreStrings)
          .developer(game.getDeveloper())
          .releasedDateTime(game.getReleasedDateTime())
          .releaseStatus(game.getReleaseStatus())
          .build();
    }
  }

}
