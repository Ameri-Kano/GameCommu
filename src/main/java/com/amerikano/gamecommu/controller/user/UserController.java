package com.amerikano.gamecommu.controller.user;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.amerikano.gamecommu.domain.dto.user.PasswordChangeForm;
import com.amerikano.gamecommu.domain.dto.user.UpdateUserForm;
import com.amerikano.gamecommu.encryption.JwtAuthService;
import com.amerikano.gamecommu.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtAuthService authService;

  @PatchMapping
  public ResponseEntity<String> updateUser(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody UpdateUserForm updateCustomerDto
  ) {
    return ResponseEntity.ok(
        userService.updateUser(
            authService.getIdFromToken(token),
            updateCustomerDto
        )
    );
  }

  @PatchMapping("password")
  public ResponseEntity<String> changeUserPassword(
      @RequestHeader(name = AUTHORIZATION) String token,
      @RequestBody PasswordChangeForm form
  ) {

    userService.changeUserPassword(authService.getIdFromToken(token), form);
    return ResponseEntity.ok("비밀번호 변경이 완료되었습니다.");
  }

  @DeleteMapping
  public ResponseEntity<String> deleteUser(
      @RequestHeader(name = AUTHORIZATION) String token
  ) {
    userService.deleteUser(authService.getIdFromToken(token));
    return ResponseEntity.ok("유저 탈퇴가 완료되었습니다.");
  }

}
