package com.saas.billing_system.plan.infrastructure.dto.response.feature;

import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Feature;

public record FeatureUpdateResponseDto(
    String id,
    String name,
    String description,
    long rateLimit,
    UUID plan) {

  public static FeatureUpdateResponseDto fromFeature(Feature feature) {
    return new FeatureUpdateResponseDto(
        feature.getId().id().toString(),
        feature.getName(),
        feature.getDescription(),
        feature.getRateLimit(),
        feature.getPlan());
  }
}
