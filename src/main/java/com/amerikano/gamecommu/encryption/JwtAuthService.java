package com.amerikano.gamecommu.encryption;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthService {

  private final String SECRET_KEY = "GameCommu_SecretKey_ALSO32Byte!#";
  private final long TOKEN_VALID_TIME = 1000L * 60 * 60 * 24; // 만료 시간 : 1일

  /**
   * 로그인 인증 정보를 담은 JWT Token 생성
   *
   * <p>Param: 유저 고유 아이디, 이메일</p>
   * <p>Return: JWT Token String</p>
   */
  public String createToken(Long id, String email) {
    Claims claims = Jwts.claims()
        .setId(id.toString())
        .setSubject(email);

    Date nowDate = new Date();
    byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    Key key = Keys.hmacShaKeyFor(keyBytes);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(nowDate)
        .setExpiration(new Date(nowDate.getTime() + TOKEN_VALID_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * 토큰에서 유저 정보(UserInfo) 가져오기
   *
   * <p>Param: JWT Token String</p>
   * <p>Return: UserInfo(id, email)</p>
   */
  public UserInfo getUserInfoFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
        .build()
        .parseClaimsJws(token)
        .getBody();

    return new UserInfo(
        Long.valueOf(claims.getId()), claims.getSubject()
    );
  }

  /**
   * 토큰을 분석하여 유효 여부를 확인
   *
   * <p>Param: JWT Token String</p>
   * <p>Return: 토큰의 유효 여부</p>
   */
  public boolean validateToken(String token) {
    try {
      Jws<Claims> parsedClaimsJws = Jwts.parserBuilder()
          .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
          .build()
          .parseClaimsJws(token);

      return !parsedClaimsJws.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * JWT 토큰에서 고유 id 값 얻어오기
   *
   * <p>Param: JWT Token String</p>
   * <p>Return: 토큰에 포함된 유저(점장, 손님) 고유 id</p>
   */
  public Long getIdFromToken(String token) {
    return getUserInfoFromToken(token.substring(7)).getId();
  }
}
