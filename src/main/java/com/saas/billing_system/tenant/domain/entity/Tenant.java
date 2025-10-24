package com.saas.billing_system.tenant.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.tenant.domain.vo.Address;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;
import com.saas.billing_system.tenant.domain.vo.TenantId;
import com.saas.billing_system.tenant.domain.vo.TenantStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private TenantStatus status;

  @Column(name = "user_id")
  private UUID user;

}
