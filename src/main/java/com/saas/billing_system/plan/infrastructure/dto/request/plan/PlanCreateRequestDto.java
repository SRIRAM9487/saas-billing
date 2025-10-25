package com.saas.billing_system.plan.infrastructure.dto.request.plan;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;

public record PlanCreateRequestDto(
    String name,
    String description,
    String defaultCurrency,
    long basePrice,
    long taxPrice,
    boolean taxIncluded,
    int durationInDays,
    String quality,
    List<FeatureCreateRequestDto> features) {

  public static Plan create(String tenant, PlanCreateRequestDto requestDto) {
    return Plan.create(
        tenant,
        requestDto.name(),
        requestDto.description(),
        requestDto.defaultCurrency(),
        requestDto.basePrice(),
        requestDto.taxPrice(),
        requestDto.taxIncluded(),
        requestDto.durationInDays(),
        requestDto.quality());
  }

}
