package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDeleteResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanDeleteUseCase {

  private static final Logger log = LoggerFactory.getLogger(PlanDeleteUseCase.class);

  private final PlanService planService;
  private final FeatureService featureService;

  public PlanDeleteResponseDto delete(String planId) {
    log.debug("Use case: Deleting plan ID: {}", planId);
    Plan plan = planService.delete(planId);
    List<Feature> features = featureService.deleteByPlanId(planId);
    PlanDeleteResponseDto response = PlanDeleteResponseDto.fromPlan(plan, features);
    log.info("Plan deletion use case completed for plan ID: {}, {} features deleted", planId, features.size());
    return response;
  }
}
