package com.saas.billing_system.tenant.domain.entity;

import java.util.UUID;

import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.tenant.domain.vo.Address;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;
import com.saas.billing_system.tenant.domain.vo.TenantId;
import com.saas.billing_system.user.domain.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tenant")
public class Tenant extends SoftDelete {

  @EmbeddedId
  private TenantId id;

  @Column(name = "name", nullable = false)
  private String name;

  @Embedded
  private Address address;

  @Column(name = "defaultCurrency", nullable = false)
  private DefaultCurrency defaultCurrency;

  @Column(name = "apiKey", nullable = false)
  private String apiKey;

  @Column(name = "user_id")
  private UUID user;

  public static Tenant create(String name,
      String addressLine1,
      String addressLine2,
      String city,
      String district,
      String state,
      String postalCode,
      String country,
      int currency,
      String apiKey,
      UUID userId) {

    return Tenant
        .builder()
        .id(TenantId.create())
        .name(name)
        .address(Address.create(addressLine1, addressLine2, city, district, state, postalCode, country))
        .user(userId)
        .defaultCurrency(DefaultCurrency.fromId(currency))
        .apiKey(apiKey)
        .build();
  }
}
