package com.amerikano.gamecommu.config.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.amerikano.gamecommu.encryption.JwtAuthService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@WebFilter(value = {"/user/*"})
@RequiredArgsConstructor
@Slf4j
public class UserFilter implements Filter {

  private final JwtAuthService authService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String bearerToken = httpServletRequest.getHeader(AUTHORIZATION);

    if (!bearerToken.startsWith("Bearer ")) {
      throw new ServletException("올바르지 않은 접근입니다.");
    }

    String token = bearerToken.substring(7);

    if (!authService.validateToken(token)) {
      throw new ServletException("올바르지 않은 접근입니다.");
    }

    chain.doFilter(request, response);
  }
}
