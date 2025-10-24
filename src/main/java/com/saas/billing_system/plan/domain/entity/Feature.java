package com.saas.billing_system.plan.domain.entity;

import com.saas.billing_system.plan.domain.vo.FeatureId;
import com.saas.billing_system.shared.domain.SoftDelete;

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
@Table(name = "feature")
public class Feature extends SoftDelete {
  @EmbeddedId
  private FeatureId id;
  @Column(name = "feature_name", nullable = false)
  private String name;

  @Column(name = "remainder", nullable = false)
  private double remainder;

  @Column(name = "rate_limit", nullable = false)
  private double rateLimit;

  @Column(name = "current", nullable = false)
  private double current;

}
