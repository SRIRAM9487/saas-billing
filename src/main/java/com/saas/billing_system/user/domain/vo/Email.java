package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.EmailException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Email(@Column(name = "email", nullable = false) String value) {

  public Email {
    validate(value);
  }

  public static boolean isEmail(String email) {
    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
      return false;
    return true;
  }

  public static Email create(String email) {
    return new Email(email);

  }

  public static void validate(String email) {
    if (email == null || email.isBlank()) {
      throw EmailException.empty();
    }

    int atCount = email.length() - email.replace("@", "").length();
    if (atCount == 0)
      throw EmailException.missingAtSymbol(email);
    if (atCount > 1)
      throw EmailException.multipleAtSymbols(email);

    String[] parts = email.split("@", 2);
    String local = parts[0];
    String domain = parts[1];

    if (local.isEmpty())
      throw EmailException.emptyLocalPart(email);
    if (!local.matches("[A-Za-z0-9!#$%&'*+/=?^_`{|}~.-]+"))
      throw EmailException.invalidLocalChars(email);
    if (local.startsWith(".") || local.endsWith("."))
      throw EmailException.leadingOrTrailingDot(email);
    if (local.contains(".."))
      throw EmailException.consecutiveDots(email);

    if (domain.isEmpty())
      throw EmailException.emptyDomainPart(email);
    if (!domain.matches("[A-Za-z0-9.-]+"))
      throw EmailException.invalidDomainChars(email);

    String[] labels = domain.split("\\.");
    for (String label : labels) {
      if (label.isEmpty())
        throw EmailException.invalidDomainFormat(email);
    }

  }

}
