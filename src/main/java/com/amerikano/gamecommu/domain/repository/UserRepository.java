package com.amerikano.gamecommu.domain.repository;

import com.amerikano.gamecommu.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

}
