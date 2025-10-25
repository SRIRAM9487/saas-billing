package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureUpdateResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureUpdateUseCase {

  private static final Logger log = LoggerFactory.getLogger(FeatureUpdateUseCase.class);

  private final FeatureService featureService;

  public FeatureUpdateResponseDto update(String featureId, FeatureCreateRequestDto requestDto) {
    log.debug("Use case: Updating feature ID: {} with data: {}", featureId, requestDto);
    Feature updatedFeature = featureService.update(featureId, requestDto);
    FeatureUpdateResponseDto response = FeatureUpdateResponseDto.fromFeature(updatedFeature);
    log.info("Feature update use case completed for ID: {}", featureId);
    return response;
  }
}
