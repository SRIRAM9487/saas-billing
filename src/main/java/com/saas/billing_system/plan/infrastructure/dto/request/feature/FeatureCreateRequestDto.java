package com.saas.billing_system.plan.infrastructure.dto.request.feature;

import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Feature;

public record FeatureCreateRequestDto(
    String name,
    String description,
    long rateLimit,
    UUID plan) {

  public static Feature toFeature(FeatureCreateRequestDto requestDto) {
    return Feature.create(
        requestDto.name(),
        requestDto.description(),
        requestDto.rateLimit(),
        requestDto.plan());
  }
}
