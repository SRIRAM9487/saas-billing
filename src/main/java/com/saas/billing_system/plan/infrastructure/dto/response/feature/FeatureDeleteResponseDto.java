package com.saas.billing_system.plan.infrastructure.dto.response.feature;

import com.saas.billing_system.plan.domain.entity.Feature;

public record FeatureDeleteResponseDto(
  String feature
) {

  public static FeatureDeleteResponseDto fromuser(Feature feature){
    return new FeatureDeleteResponseDto(feature.getName());
  }
}
