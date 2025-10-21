package com.saas.billing_system.user.domain.entity;

import com.saas.billing_system.user.domain.vo.Email;
import com.saas.billing_system.user.domain.vo.Password;
import com.saas.billing_system.user.domain.vo.UserId;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Users")
@Getter
public class User {

  @EmbeddedId
  private UserId id;

  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  @Embedded
  private Password password;

  @Embedded
  private Email email;

  @Column(name = "role", nullable = false)
  private UserRole role;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "account_non_locked")
  private boolean accountNonLocked;

  @Column(name = "credentials_non_expired")
  private boolean credentialsNonExpired;

  @Column(name = "account_non_expired")
  private boolean accountNonExpired;

  public static User createTenant(String userName, String password) {
    return User
        .builder()
        .id(UserId.create())
        .userName(userName)
        .password(Password.create(password))
        .role(UserRole.TENANT)
        .enabled(true)
        .accountNonLocked(true)
        .credentialsNonExpired(true)
        .accountNonExpired(true)
        .build();
  }

  public static User createSudo(String userName, String password) {
    return User
        .builder()
        .id(UserId.create())
        .userName(userName)
        .password(Password.create(password))
        .role(UserRole.SUDO)
        .enabled(true)
        .accountNonLocked(true)
        .credentialsNonExpired(true)
        .accountNonExpired(true)
        .build();
  }

  public static User createSystem(String userName, String password) {
    return User
        .builder()
        .id(UserId.create())
        .userName(userName)
        .password(Password.create(password))
        .role(UserRole.SYSTEM)
        .enabled(true)
        .accountNonLocked(true)
        .credentialsNonExpired(true)
        .accountNonExpired(true)
        .build();
  }

  public void changePassword(String password) {
    this.createPassword(password);
  }

  public void createPassword(String password) {
    this.password = Password.create(password);
  }
}
