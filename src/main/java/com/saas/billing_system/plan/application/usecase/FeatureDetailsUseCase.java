package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureFactory;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDetailsResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureDetailsUseCase {

  private static final Logger log = LoggerFactory.getLogger(FeatureDetailsUseCase.class);

  private final FeatureFactory featureFactory;

  public List<FeatureDetailsResponseDto> getAll() {
    log.debug("Use case: Retrieving all feature details");
    List<FeatureDetailsResponseDto> dtos = featureFactory.getAll().stream()
        .map(FeatureDetailsResponseDto::fromFeature)
        .toList();
    log.info("Retrieved {} feature details", dtos.size());
    return dtos;
  }

  public FeatureDetailsResponseDto getById(String featureId) {
    log.debug("Use case: Retrieving feature details by ID: {}", featureId);
    FeatureDetailsResponseDto dto = FeatureDetailsResponseDto.fromFeature(featureFactory.getById(featureId));
    log.info("Feature details retrieved for ID: {}", featureId);
    return dto;
  }

  public List<FeatureDetailsResponseDto> getByPlanId(String planId) {
    log.debug("Use case: Retrieving feature details by plan ID: {}", planId);
    List<FeatureDetailsResponseDto> dtos = featureFactory.getAllByPlan(planId).stream()
        .map(FeatureDetailsResponseDto::fromFeature)
        .toList();
    log.info("Retrieved {} feature details for plan ID: {}", dtos.size(), planId);
    return dtos;
  }
}
