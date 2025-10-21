package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.EmailException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Email(@Column(name = "email", nullable = false) String value) {

  public Email {
    if (value == null || value.isBlank()) {
      throw EmailException.empty();
    }
  }

  public static boolean isEmail(String email) {
    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
      return false;
    return true;
  }

  public static Email create(String email){
    return new Email(email);

  }
}
