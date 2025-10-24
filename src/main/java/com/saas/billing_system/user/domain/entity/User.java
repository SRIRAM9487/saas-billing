package com.saas.billing_system.user.domain.entity;

import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.user.domain.vo.Email;
import com.saas.billing_system.user.domain.vo.Password;
import com.saas.billing_system.user.domain.vo.PhoneNumber;
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
import lombok.ToString;

@Entity
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User extends SoftDelete {

  @EmbeddedId
  private UserId id;

  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  @Embedded
  private Password password;

  @Embedded
  private Email email;

  @Column(name = "verified", nullable = false)
  private VerifiedType verified;

  @Embedded
  private PhoneNumber phone;

  @Column(name = "role", nullable = false)
  private UserRole role;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "account_non_locked")
  private boolean accountNonLocked;

  public static User create(String userName, String password, String email, String number, String role) {
    return User
        .builder()
        .id(UserId.create())
        .userName(userName)
        .password(Password.create(password))
        .email(Email.create(email))
        .phone(PhoneNumber.create(number))
        .role(UserRole.valueOf(role))
        .verified(VerifiedType.PENDING)
        .enabled(true)
        .accountNonLocked(true)
        .build();
  }

  public static User create(String userName, String password, String email, String number, UserRole role) {
    return User
        .builder()
        .id(UserId.create())
        .userName(userName)
        .password(Password.create(password))
        .email(Email.create(email))
        .phone(PhoneNumber.create(number))
        .role(role)
        .verified(VerifiedType.PENDING)
        .enabled(true)
        .accountNonLocked(true)
        .build();
  }

  public static User createTenant(String userName, String password, String email, String number) {
    return create(userName, password, email, number, UserRole.TENANT);
  }

  public static User createSudo(String userName, String password, String email, String number) {
    return create(userName, password, email, number, UserRole.SUDO);
  }

  public static User createAdmin(String userName, String password, String email, String number) {
    return create(userName, password, email, number, UserRole.ADMIN);
  }

  public static User createSystem(String userName, String password, String email, String number) {
    return create(userName, password, email, number, UserRole.SYSTEM);
  }

  public void updatePassword(String password) {
    this.password = Password.create(password);
  }

  public void verifyEmail() {
    this.verified = VerifiedType.VERIFIED;
  }

  public void updateEmail(String email) {
    this.email = Email.create(email);
    this.verified = VerifiedType.PENDING;
  }

  public void updatePhone(String number) {
    this.phone = PhoneNumber.create(number);
  }

  public boolean isVerified() {
    return this.verified.isVerified();
  }

  public void toggleLock() {
    this.accountNonLocked = !this.accountNonLocked;
  }

  public boolean isLocked() {
    return !this.accountNonLocked;
  }

}
