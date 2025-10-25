package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureUpdateResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureUpdateUseCase {

  private final FeatureService featureService;

  public FeatureUpdateResponseDto update(String featureId, FeatureCreateRequestDto requestDto) {
    Feature updatedFeature = featureService.update(featureId, requestDto);
    return FeatureUpdateResponseDto.fromFeature(updatedFeature);
  }

}
