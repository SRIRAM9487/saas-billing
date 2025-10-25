package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureCreateResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureCreationUseCase {

  private final FeatureService featureService;

  public FeatureCreateResponseDto create(FeatureCreateRequestDto requestDto) {
    Feature savedFeature = featureService.create(requestDto);
    return FeatureCreateResponseDto.fromFeature(savedFeature);
  }

}
