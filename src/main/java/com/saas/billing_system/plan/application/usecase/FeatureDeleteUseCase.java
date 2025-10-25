package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDeleteResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureDeleteUseCase {

  private final FeatureService featureService;

  public FeatureDeleteResponseDto delete(String featureId) {
    Feature feature = featureService.delete(featureId);
    return FeatureDeleteResponseDto.fromFeature(feature);
  }

}
