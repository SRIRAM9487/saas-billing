package com.saas.billing_system.plan.infrastructure.dto.request.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureUpdateResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanUpdateResponseDto;

public record PlanUpdateRequestDto(
    String id,
    String name,
    String description,
    String defaultCurrency,
    long basePrice,
    double taxPrice,
    double totalPrice,
    boolean taxIncluded,
    int durationInDays,
    String quality,
    List<FeatureUpdateRequestDto> features) {

  public static PlanUpdateResponseDto create(Plan plan, List<Feature> feature) {
    List<FeatureUpdateResponseDto> features = feature
        .stream()
        .map(feat -> FeatureUpdateResponseDto.fromFeature(feat))
        .toList();
    return new PlanUpdateResponseDto(
        plan.getId().id().toString(),
        plan.getName(),
        plan.getDescription(),
        plan.getDefaultCurrency().name(),
        plan.getBasePrice(),
        plan.getTaxPrice(),
        plan.getTotalPrice(),
        plan.isTaxIncluded(),
        plan.getDurationInDays(),
        plan.getRemainingDays(),
        plan.getQuality().name(),
        features);
  }

}
