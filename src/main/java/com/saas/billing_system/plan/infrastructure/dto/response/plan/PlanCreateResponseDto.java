package com.saas.billing_system.plan.infrastructure.dto.response.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureCreateResponseDto;

public record PlanCreateResponseDto(
    String name,
    String description,
    String planId,
    List<FeatureCreateResponseDto> features) {

  public static PlanCreateResponseDto toResponse(Plan plan, List<Feature> features) {
    List<FeatureCreateResponseDto> feature = features
        .stream()
        .map(FeatureCreateResponseDto::fromFeature)
        .toList();
    return new PlanCreateResponseDto(
        plan.getName(),
        plan.getDescription(),
        plan.getId().id().toString(),
        feature);
  }
}
