package com.saas.billing_system.plan.application.usecase;

import java.util.ArrayList;
import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanUpdateResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanUpdateUseCase {

  private final PlanService planService;
  private final FeatureService featureService;

  public PlanUpdateResponseDto planUpdateUseCase(String planId, PlanUpdateRequestDto requestDto) {

    Plan plan = planService.update(planId, requestDto);
    List<Feature> features = new ArrayList<>();

    for (FeatureUpdateRequestDto dto : requestDto.features())
      features.add(featureService.update(dto));

    return PlanUpdateRequestDto.create(plan, features);
  }

}
