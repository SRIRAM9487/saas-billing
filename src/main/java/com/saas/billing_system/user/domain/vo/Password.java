package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.InvalidPasswordException;

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
      throw InvalidPasswordException.isEmpty();

    if (hash.length() < 8)
      throw InvalidPasswordException.requireAtleastEightCharacter();

    if (!hash.matches(".*[A-Z].*"))
      throw InvalidPasswordException.requireAtleastOneCapitalLetter();

    if (!hash.matches(".*[a-z].*"))
      throw InvalidPasswordException.requireAtleastOneLowerCaseLetter();

    if (!hash.matches(".*[0-9].*"))
      throw InvalidPasswordException.requireAtleastOneNumber();

    return true;
  }

  public static Password create(String password){
    return new Password(password);
  }

}
