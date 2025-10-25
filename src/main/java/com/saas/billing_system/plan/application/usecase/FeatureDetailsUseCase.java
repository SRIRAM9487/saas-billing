package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureFactory;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDetailsResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureDetailsUseCase {

  private final FeatureFactory featureFactory;

  public List<FeatureDetailsResponseDto> getAll() {
    return featureFactory
        .getAll()
        .stream()
        .map(FeatureDetailsResponseDto::fromFeature)
        .toList();
  }

  public FeatureDetailsResponseDto getById(String planId) {
    return FeatureDetailsResponseDto.fromFeature(featureFactory.getById(planId));
  }

  public List<FeatureDetailsResponseDto> getByPlanId(String planId) {
    return featureFactory
        .getAllByPlan(planId)
        .stream()
        .map(FeatureDetailsResponseDto::fromFeature)
        .toList();
  }
}
