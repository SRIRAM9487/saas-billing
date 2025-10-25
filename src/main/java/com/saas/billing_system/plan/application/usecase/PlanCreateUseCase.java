package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanCreateResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanCreateUseCase {

  private static final Logger log = LoggerFactory.getLogger(PlanCreateUseCase.class);

  private final PlanService planService;
  private final FeatureService featureService;

  public PlanCreateResponseDto create(String tenantId, PlanCreateRequestDto requestDto) {
    log.debug("Use case: Creating plan for tenant: {} with {} features", tenantId, requestDto.features().size());
    Plan plan = planService.create(tenantId, requestDto);
    List<Feature> features = featureService.create(plan.getId().id().toString(), requestDto.features());
    PlanCreateResponseDto response = PlanCreateResponseDto.toResponse(plan, features);
    log.info("Plan creation use case completed for plan ID: {}", plan.getId());
    return response;
  }
}
