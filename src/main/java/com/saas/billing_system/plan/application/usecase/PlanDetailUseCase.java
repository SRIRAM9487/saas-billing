package com.saas.billing_system.plan.application.usecase;

import java.util.ArrayList;
import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDetailsResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanDetailUseCase {

  private final PlanService planService;
  private final FeatureService featureService;

  public List<PlanDetailsResponseDto> getAll() {

    List<Plan> plans = planService.getAll();

    List<PlanDetailsResponseDto> responseDtos = new ArrayList<>();

    for (Plan plan : plans) {
      List<Feature> features = featureService.getAllByPlan(plan.getId().id().toString());
      responseDtos.add(PlanDetailsResponseDto.fromPlan(plan, features));
    }

    return responseDtos;
  }

  public PlanDetailsResponseDto getById(String id) {
    Plan plan = planService.getById(id);
    List<Feature> features = featureService.getAllByPlan(id);
    return PlanDetailsResponseDto.fromPlan(plan, features);
  }

}
