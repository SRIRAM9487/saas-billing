package com.saas.billing_system.tenant.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.tenant.domain.vo.Address;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;
import com.saas.billing_system.tenant.domain.vo.TenantId;

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

  @Column(name = "default_currency", nullable = false)
  private DefaultCurrency defaultCurrency;

  @Column(name = "api_key", nullable = true)
  private String apiKey;

  @Column(name = "user_id", nullable = true)
  private UUID user;

  @Column(name = "api_created_by", nullable = true)
  private UUID apiKeyCreatedBy;

  @Column(name = "api_created_at", nullable = true)
  private LocalDateTime apiKeyCreatedAt;

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

  public static Tenant create(String name,
      String addressLine1,
      String addressLine2,
      String city,
      String district,
      String state,
      String postalCode,
      String country,
      int currency,
      UUID userId) {

    return Tenant
        .builder()
        .id(TenantId.create())
        .name(name)
        .address(Address.create(addressLine1, addressLine2, city, district, state, postalCode, country))
        .user(userId)
        .defaultCurrency(DefaultCurrency.fromId(currency))
        .build();
  }

  public void updateApikey(String apiKey) {
    this.apiKey = apiKey;
    this.apiKeyCreatedBy = UserContextHolder.get().getUserId();
    this.apiKeyCreatedAt = LocalDateTime.now();
  }
}
