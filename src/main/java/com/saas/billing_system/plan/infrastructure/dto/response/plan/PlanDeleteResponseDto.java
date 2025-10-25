package com.saas.billing_system.plan.infrastructure.dto.response.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDeleteResponseDto;

public record PlanDeleteResponseDto(
    String id,
    List<FeatureDeleteResponseDto> features) {

  public static PlanDeleteResponseDto fromPlan(Plan plan, List<Feature> features) {
    List<FeatureDeleteResponseDto> feature = features.stream().map(FeatureDeleteResponseDto::fromFeature).toList();
    return new PlanDeleteResponseDto(plan.getId().id().toString(), feature);
  }
}
