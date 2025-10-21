package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.PhoneException;

import jakarta.persistence.Column;

public record PhoneNumber(@Column(name = "phone", nullable = false) String value) {

  public PhoneNumber {
    validate(value);
  }

  public static PhoneNumber create(String number) {
    return new PhoneNumber(number);
  }

  public void validate(String number) {

    if (number == null || number.isBlank())
      throw PhoneException.empty();

    String normalized = number.replaceAll("[\\s\\-]", "");

    if (normalized.matches("\\+?\\d+"))
      throw PhoneException.invalidCharacters();

    String countryCode = "";
    String localNumber = "";

    if (countryCode == null || countryCode.isBlank())
      throw PhoneException.invalidCountryCode();

    if (localNumber == null || localNumber.isBlank())
      throw PhoneException.invalidPhoneNumber();

    if (localNumber.length() < 8)
      throw PhoneException.tooShort();

    if (localNumber.length() > 15)
      throw PhoneException.tooLong();

  }

}
