package com.amerikano.gamecommu.service.gameinfo;

import static com.amerikano.gamecommu.domain.type.ReleaseStatus.DELETED;

import com.amerikano.gamecommu.domain.dto.game.GameInfo.RequestDto;
import com.amerikano.gamecommu.domain.dto.game.GameInfo.ResponseDto;
import com.amerikano.gamecommu.domain.dto.game.ModifyGame;
import com.amerikano.gamecommu.domain.entity.Game;
import com.amerikano.gamecommu.domain.entity.Genre;
import com.amerikano.gamecommu.domain.repository.GameRepository;
import com.amerikano.gamecommu.domain.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameInfoService {

  private final GameRepository gameRepository;
  private final GenreRepository genreRepository;

  public ResponseDto registerGameInfo(RequestDto requestDto) {
    checkTitleExists(requestDto.getTitle());

    List<Genre> genres = checkAndGetGenres(requestDto);

    Game newGame = Game.builder()
        .title(requestDto.getTitle())
        .genres(genres)
        .developer(requestDto.getDeveloper())
        .releasedDateTime(requestDto.getReleasedDateTime())
        .releaseStatus(requestDto.getReleaseStatus())
        .build();

    gameRepository.save(newGame);

    List<String> genreStrings = getGenreStrings(genres);

    return ResponseDto.from(newGame, genreStrings);
  }


  @Transactional
  public ResponseDto modifyGameInfo(ModifyGame.RequestDto requestDto) {
    Game game = checkGame(requestDto.getId());

    checkTitleExists(requestDto.getTitle());

    List<Genre> genres = checkAndGetGenres(requestDto);

    game.setTitle(requestDto.getTitle());
    game.setDeveloper(requestDto.getDeveloper());
    game.setGenres(genres);
    game.setReleasedDateTime(requestDto.getReleasedDateTime());
    game.setReleaseStatus(requestDto.getReleaseStatus());

    List<String> genreStrings = getGenreStrings(genres);

    return ResponseDto.from(game, genreStrings);
  }

  @Transactional
  public String deleteGameInfo(Long id) {
    Game game = checkGame(id);

    game.setReleaseStatus(DELETED);

    return game.getTitle() + " 의 정보 삭제가 완료되었습니다.";
  }

  private void checkTitleExists(String title) {
    if (gameRepository.existsByTitle(title)) {
      throw new RuntimeException("해당 타이틀을 가지는 게임이 이미 존재합니다.");
    }
  }

  private Game checkGame(Long id) {
    Game game = gameRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("해당하는 게임의 id가 존재하지 않습니다."));

    if (game.getReleaseStatus() == DELETED) {
      throw new RuntimeException("이미 삭제 처리된 게임 정보입니다.");
    }
    return game;
  }

  private List<Genre> checkAndGetGenres(RequestDto requestDto) {
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
    return genres;
  }

  private List<String> getGenreStrings(List<Genre> genres) {
    return genres.stream().map(Genre::getName).sorted().toList();
  }

}
