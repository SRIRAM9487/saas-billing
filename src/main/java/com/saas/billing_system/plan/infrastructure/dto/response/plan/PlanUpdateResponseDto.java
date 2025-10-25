package com.saas.billing_system.plan.infrastructure.dto.response.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureUpdateResponseDto;

public record PlanUpdateResponseDto(
    String id,
    String name,
    String description,
    String defaultCurrency,
    long basePrice,
    double taxPrice,
    double totalPrice,
    boolean taxIncluded,
    int durationInDays,
    double remaindingDays,
    String quality,
    List<FeatureUpdateResponseDto> features) {

  public static PlanUpdateResponseDto toDto(Plan plan, List<Feature> features) {

    List<FeatureUpdateResponseDto> feature = features
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
        feature);
  }
}
