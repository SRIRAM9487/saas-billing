package com.saas.billing_system.plan.application.usecase;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDeleteResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureDeleteUseCase {

  private static final Logger log = LoggerFactory.getLogger(FeatureDeleteUseCase.class);

  private final FeatureService featureService;

  public FeatureDeleteResponseDto delete(String featureId) {
    log.debug("Use case: Deleting feature ID: {}", featureId);
    Feature feature = featureService.delete(featureId);
    FeatureDeleteResponseDto response = FeatureDeleteResponseDto.fromFeature(feature);
    log.info("Feature deletion use case completed for ID: {}", featureId);
    return response;
  }
}
