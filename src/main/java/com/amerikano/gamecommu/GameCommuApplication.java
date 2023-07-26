package com.amerikano.gamecommu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GameCommuApplication {

  public static void main(String[] args) {
    SpringApplication.run(GameCommuApplication.class, args);
  }

}
