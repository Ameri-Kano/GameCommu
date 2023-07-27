package com.amerikano.gamecommu.service.gameinfo;

import com.amerikano.gamecommu.domain.dto.game.RegisterGame.RequestDto;
import com.amerikano.gamecommu.domain.dto.game.RegisterGame.ResponseDto;
import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.entity.Genre;
import com.amerikano.gamecommu.domain.repository.GameRepository;
import com.amerikano.gamecommu.domain.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameInfoService {

  private final GameRepository gameRepository;
  private final GenreRepository genreRepository;

  public ResponseDto registerGameInfo(RequestDto requestDto) {
    if (gameRepository.existsByTitle(requestDto.getTitle())) {
      throw new RuntimeException("해당 타이틀을 가지는 게임이 이미 존재합니다.");
    }

    List<Genre> genres = new ArrayList<>();
    for (Long gid : requestDto.getGenres()) {
      if (gid == null) {
        throw new RuntimeException("장르 값은 null 일 수 없습니다.");
      }
      Genre genre = genreRepository.findById(gid)
          .orElseThrow(() -> new RuntimeException("해당하는 장르가 존재하지 않습니다."));
      genres.add(genre);
    }

    if (genres.isEmpty()) {
      throw new RuntimeException("장르 항목은 비어있을 수 없습니다.");
    }

    Game newGame = Game.builder()
        .title(requestDto.getTitle())
        .genres(genres)
        .developer(requestDto.getDeveloper())
        .releasedDateTime(requestDto.getReleasedDateTime())
        .releaseStatus(requestDto.getReleaseStatus())
        .build();

    gameRepository.save(newGame);

    List<String> genreStrings = genres.stream().map(Genre::getName).sorted().toList();

    return ResponseDto.builder()
        .title(newGame.getTitle())
        .genres(genreStrings)
        .developer(newGame.getDeveloper())
        .releasedDateTime(newGame.getReleasedDateTime())
        .releaseStatus(newGame.getReleaseStatus())
        .build();
  }
}
