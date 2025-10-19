package com.saas.billing_system.user.domain.entity;

import java.util.Collection;
import java.util.Collections;

import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.user.domain.vo.Password;
import com.saas.billing_system.user.domain.vo.UserId;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Users")
public class UserImpl extends SoftDelete implements UserDetails {

  @EmbeddedId
  private UserId id;

  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  @Embedded
  private Password password;

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  public String getPassword() {
    return this.password.hash();
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  public static UserImpl createTenant(String userName, String password) {
    return UserImpl
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

  public static UserImpl createSudo(String userName, String password) {
    return UserImpl
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

  public static UserImpl createSystem(String userName, String password) {
    return UserImpl
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
