package com.saas.billing_system.tenant.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Address(
    @Column(name = "address_line_1", nullable = false, length = 255) String addressLine1,

    @Column(name = "address_line_2", length = 255) String addressLine2,

    @Column(name = "city", nullable = false, length = 100) String city,

    @Column(name = "district", length = 100) String district,

    @Column(name = "state", nullable = false, length = 100) String state,

    @Column(name = "postal_code", nullable = false, length = 20) String postalCode,

    @Column(name = "country", nullable = false, length = 100) String country) {

  public static Address create(
      String addressLine1,
      String addressLine2,
      String city,
      String district,
      String state,
      String postalCode,
      String country) {
    return new Address(addressLine1, addressLine2, city, district, state, postalCode, country);
  }

}
