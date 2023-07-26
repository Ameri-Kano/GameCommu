package com.amerikano.gamecommu.config;

import com.amerikano.gamecommu.encryption.JwtAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public JwtAuthService jwtAuthService() {
    return new JwtAuthService();
  }
}
