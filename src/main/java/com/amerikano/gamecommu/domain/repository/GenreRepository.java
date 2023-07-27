package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
