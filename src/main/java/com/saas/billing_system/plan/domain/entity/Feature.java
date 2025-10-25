package com.saas.billing_system.plan.domain.entity;

import java.util.UUID;

import com.saas.billing_system.plan.domain.vo.FeatureId;
import com.saas.billing_system.shared.domain.SoftDelete;

import jakarta.persistence.Column;
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
@Table(name = "feature")
public class Feature extends SoftDelete {

  @EmbeddedId
  private FeatureId id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "rate_limit", nullable = false)
  private long rateLimit;

  @Column(name = "plan_id", nullable = false)
  private UUID plan;

  public static Feature create(
      String name,
      String description,
      long rateLimit,
      UUID plan) {

    return Feature
        .builder()
        .name(name)
        .description(description)
        .rateLimit(rateLimit)
        .plan(plan)
        .build();
  }

}
