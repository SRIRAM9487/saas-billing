package com.saas.billing_system.plan.domain.entity;

import java.util.UUID;

import com.saas.billing_system.plan.domain.vo.PlanId;
import com.saas.billing_system.plan.domain.vo.PlanQuality;
import com.saas.billing_system.shared.domain.SoftDelete;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "plan")
public class Plan extends SoftDelete {

  @EmbeddedId
  private PlanId id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "default_currency", nullable = false)
  private DefaultCurrency defaultCurrency;

  @Column(name = "base_price", nullable = false)
  private long basePrice;

  @Column(name = "tax_price", nullable = false)
  private double taxPrice;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "tax_included", nullable = false)
  private boolean taxIncluded;

  @Column(name = "duration_in_days", nullable = false)
  private int durationInDays;

  @Column(name = "remaiding_days", nullable = false)
  private double remaindingDays;

  @Column(name = "quality", nullable = false)
  private PlanQuality quality;

  @Column(name = "tenant_id",nullable = false)
  private UUID tenant;
}
