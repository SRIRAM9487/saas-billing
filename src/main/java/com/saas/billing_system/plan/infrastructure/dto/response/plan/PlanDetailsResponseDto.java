package com.saas.billing_system.plan.infrastructure.dto.response.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDetailsResponseDto;

public record PlanDetailsResponseDto(
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
    String tenantId,
    List<FeatureDetailsResponseDto> features) {

  public static PlanDetailsResponseDto fromPlan(Plan plan, List<Feature> features) {

    List<FeatureDetailsResponseDto> feature = features.stream()
        .map(feat -> FeatureDetailsResponseDto.fromFeature(feat)).toList();
    return new PlanDetailsResponseDto(
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
        plan.getTenant().toString(),
        feature);
  }
}
