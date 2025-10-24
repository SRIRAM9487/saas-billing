package com.saas.billing_system.plan.domain.entity;

import com.saas.billing_system.plan.domain.vo.PlanId;
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
  private String planName;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "tax_included", nullable = false)
  private boolean taxIncluded;

  @Column(name = "currency_type", nullable = false)
  private DefaultCurrency defaultCurrency;

  @Column(name = "base_price", nullable = false)
  private long basePrice;

  @Column(name = "tax_price", nullable = false)
  private double taxPrice;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "plan_days", nullable = false)
  private int days;

  @Column(name = "plan_months", nullable = false)
  private byte months;

  @Column(name = "plan_years", nullable = false)
  private double years;

  @Column(name = "remaider", nullable = false)
  private double remainderDate;


}
