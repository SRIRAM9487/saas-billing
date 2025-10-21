package com.saas.billing_system.user.domain.vo;

import com.saas.billing_system.user.domain.exception.PhoneNumberException;

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
      throw PhoneNumberException.empty();

    String normalized = number.replaceAll("[\\s\\-]", "");

    if (!normalized.matches("\\+?\\d+"))
      throw PhoneNumberException.invalidCharacters();

    String countryCode = "";
    String localNumber = "";

    // if (normalized.startsWith("+")) {
    // int i = 1;
    // while (i < normalized.length() && Character.isDigit(normalized.charAt(i)))
    // i++;
    //
    // countryCode = normalized.substring(0, i);
    // localNumber = normalized.substring(i);
    // }
    countryCode = normalized.substring(0, 3);
    localNumber = normalized.substring(3);
    if (countryCode == null || countryCode.isBlank())
      throw PhoneNumberException.invalidCountryCode();

    if (localNumber == null || localNumber.isBlank())
      throw PhoneNumberException.invalidPhoneNumber();

    if (localNumber.length() < 8)
      throw PhoneNumberException.tooShort();

    if (localNumber.length() > 15)
      throw PhoneNumberException.tooLong();

  }

}
