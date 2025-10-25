package com.saas.billing_system.plan.domain.vo;

import java.util.UUID;

public record FeatureId(UUID id) {

  public static FeatureId create() {
    return new FeatureId(UUID.randomUUID());
  }

  public static FeatureId create(String id) {
    return new FeatureId(UUID.fromString(id));
  }

}
