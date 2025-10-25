package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanCreateResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanCreateUseCase {

  private final PlanService planService;
  private final FeatureService featureService;


  public PlanCreateResponseDto create(String tenantId, PlanCreateRequestDto requestDto) {
    Plan plan = planService.create(tenantId, requestDto);
    List<Feature> features = featureService.create(plan.getId().id().toString(), requestDto.features());

    return PlanCreateResponseDto.toResponse(plan, features);
  }

}
