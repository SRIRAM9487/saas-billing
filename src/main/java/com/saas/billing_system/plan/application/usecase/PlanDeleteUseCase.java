package com.saas.billing_system.plan.application.usecase;

import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDeleteResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanDeleteUseCase {

  private final PlanService planService;
  private final FeatureService featureService;

  public PlanDeleteResponseDto delete(String planId) {

    Plan plan = planService.delete(planId);
    List<Feature> features = featureService.deleteByPlanId(planId);

    return PlanDeleteResponseDto.fromPlan(plan, features);
  }

}
