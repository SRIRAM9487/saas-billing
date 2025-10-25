package com.saas.billing_system.plan.infrastructure.dto.response.feature;

import com.saas.billing_system.plan.domain.entity.Feature;

public record FeatureCreateResponseDto(
    String name,
    String description,
    long rateLimit,
    String planId) {

  public static FeatureCreateResponseDto fromFeature(Feature feature) {
    return new FeatureCreateResponseDto(
        feature.getName(),
        feature.getDescription(),
        feature.getRateLimit(),
        feature.getPlan().toString());
  }

}
