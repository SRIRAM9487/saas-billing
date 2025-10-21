package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.PasswordException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String hash) {

  private static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(12);

  public Password {
    isValid(hash);
    hash = PASSWORD_ENCODER.encode(hash);
  }

  private boolean isValid(String hash) {

    if (hash.isEmpty() || hash == null)
      throw PasswordException.isEmpty();

    if (hash.length() < 8)
      throw PasswordException.requireAtleastEightCharacter();

    if (!hash.matches(".*[A-Z].*"))
      throw PasswordException.requireAtleastOneCapitalLetter();

    if (!hash.matches(".*[a-z].*"))
      throw PasswordException.requireAtleastOneLowerCaseLetter();

    if (!hash.matches(".*[0-9].*"))
      throw PasswordException.requireAtleastOneNumber();

    return true;
  }

  public static Password create(String password){
    return new Password(password);
  }

}
