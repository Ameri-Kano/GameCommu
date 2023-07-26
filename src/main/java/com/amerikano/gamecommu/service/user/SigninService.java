package com.amerikano.gamecommu.service.user;

import com.amerikano.gamecommu.domain.dto.user.SigninForm;
import com.amerikano.gamecommu.domain.dto.user.SigninResultDto;
import com.amerikano.gamecommu.domain.entity.User;
import com.amerikano.gamecommu.domain.repository.UserRepository;
import com.amerikano.gamecommu.encryption.CryptoUtil;
import com.amerikano.gamecommu.encryption.JwtAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SigninService {

  private final UserRepository userRepository;
  private final JwtAuthService authService;

  public SigninResultDto signinUser(SigninForm form) {
    User user = userRepository.findByEmail(form.getEmail())
        .orElse(null);

    if (user == null) {
      return new SigninResultDto("해당하는 유저가 존재하지 않습니다.", null);
    }

    if (!form.getPassword().equals(CryptoUtil.decrypt(user.getPassword()))) {
      return new SigninResultDto("비밀번호가 일치하지 않습니다.", null);
    }

    return new SigninResultDto(
        user.getName() + " 님 환영합니다.",
        authService.createToken(user.getId(), user.getEmail())
    );
  }
}
