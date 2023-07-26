package com.amerikano.gamecommu.domain.entity;

import com.amerikano.gamecommu.domain.dto.user.JoinForm;
import com.amerikano.gamecommu.encryption.CryptoUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

  @Id
  @Column()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  private String name;

  private String phone;

  private LocalDate birth;

  public static User from(JoinForm joinForm) {
    return User.builder()
        .email(joinForm.getEmail())
        .password(CryptoUtil.encrypt(joinForm.getPassword()))
        .name(joinForm.getName())
        .birth(joinForm.getBirth())
        .phone(joinForm.getPhone())
        .build();
  }
}
