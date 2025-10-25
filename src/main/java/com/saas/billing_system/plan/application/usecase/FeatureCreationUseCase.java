package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureCreateResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureCreationUseCase {

  private static final Logger log = LoggerFactory.getLogger(FeatureCreationUseCase.class);

  private final FeatureService featureService;

  public FeatureCreateResponseDto create(FeatureCreateRequestDto requestDto) {
    log.debug("Use case: Creating feature with request: {}", requestDto);
    Feature savedFeature = featureService.create(requestDto);
    FeatureCreateResponseDto response = FeatureCreateResponseDto.fromFeature(savedFeature);
    log.info("Feature creation use case completed for ID: {}", savedFeature.getId());
    return response;
  }
}
