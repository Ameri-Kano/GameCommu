package com.amerikano.gamecommu.service.user;

import com.amerikano.gamecommu.domain.dto.user.JoinForm;
import com.amerikano.gamecommu.domain.entity.User;
import com.amerikano.gamecommu.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

  private final UserRepository userRepository;

  public String joinUser(JoinForm joinForm) {
    if (userRepository.existsByEmail(joinForm.getEmail())) {
      throw new RuntimeException("이미 존재하는 이메일입니다.");
    }

    User user = User.from(joinForm);
    userRepository.save(user);

    return user.getName() + " 님 가입이 완료되었습니다. 환영합니다!";
  }
}
