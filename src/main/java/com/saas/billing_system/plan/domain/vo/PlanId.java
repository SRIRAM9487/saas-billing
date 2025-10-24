package com.saas.billing_system.plan.domain.vo;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public record PlanId(UUID id) {

  public static PlanId create() {
    return new PlanId(UUID.randomUUID());
  }

}
