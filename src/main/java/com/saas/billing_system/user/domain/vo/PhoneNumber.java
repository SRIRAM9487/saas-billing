package com.saas.billing_system.user.domain.vo;

import jakarta.persistence.Column;

public record PhoneNumber(@Column(name = "phone", nullable = false) double value) {

  public PhoneNumber {
  }

  public static PhoneNumber create(double number) {
    return new PhoneNumber(number);
  }

}
