package com.saas.billing_system.plan.infrastructure.dto.response.feature;

import com.saas.billing_system.plan.domain.entity.Feature;

public record FeatureDetailsResponseDto(
    String id,
    String name,
    String descirption,
    String rateLimit,
    String plan) {

  public static FeatureDetailsResponseDto fromFeature(Feature feature) {
    return new FeatureDetailsResponseDto(
        feature.getId().id().toString(),
        feature.getName(),
        feature.getDescription(),
        String.valueOf(feature.getRateLimit()),
        feature.getPlan().toString());
  }
}
