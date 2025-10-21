package com.saas.billing_system.user.domain.entity;

import java.util.Collection;
import java.util.Collections;

import com.saas.billing_system.shared.domain.SoftDelete;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserImpl extends SoftDelete implements UserDetails {

  private User user;

  public UserImpl(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(this.user.getRole().name()));
  }

  @Override
  public String getUsername() {
    return this.user.getUserName();
  }

  @Override
  public String getPassword() {
    return this.user.getPassword().hash();
  }

  @Override
  public boolean isEnabled() {
    return this.user.isEnabled();
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.user.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.user.isCredentialsNonExpired();
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.user.isAccountNonExpired();
  }

}
