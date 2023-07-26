package com.amerikano.gamecommu.service.user;

import com.amerikano.gamecommu.domain.dto.user.PasswordChangeForm;
import com.amerikano.gamecommu.domain.dto.user.UpdateUserForm;
import com.amerikano.gamecommu.domain.entity.User;
import com.amerikano.gamecommu.domain.repository.UserRepository;
import com.amerikano.gamecommu.encryption.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public String updateUser(Long id, UpdateUserForm form) {
    // 해당하는 유저가 존재하지 않을경우
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));

    user.setName(form.getName());
    user.setEmail(form.getEmail());
    user.setPhone(form.getPhone());

    return "회원 정보 변경이 완료되었습니다.";
  }

  @Transactional
  public void deleteUser(Long id) {
    // 해당하는 유저가 존재하지 않을경우
    User customer = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));

    userRepository.delete(customer);
  }

  @Transactional
  public void changeUserPassword(Long id, PasswordChangeForm form) {
    // 해당하는 유저가 존재하지 않을경우
    User customer = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));

    String decryptedPassword = CryptoUtil.decrypt(customer.getPassword());

    // 입력받은 지금 비밀번호가 실제와 일치하지 않는 경우
    if (!decryptedPassword.equals(form.getOldPassword())) {
      throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
    }

    // 지금 비밀번호와 새 비밀번호가 같은 경우
    if (form.getOldPassword()
        .equals(form.getNewPassword())) {
      throw new RuntimeException("비밀번호가 변경되지 않았습니다.");
    }

    customer.setPassword(CryptoUtil.encrypt(form.getNewPassword()));
  }
}
