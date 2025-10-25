package com.saas.billing_system.plan.application.usecase;

import java.util.ArrayList;
import java.util.List;

import com.saas.billing_system.plan.application.service.FeatureService;
import com.saas.billing_system.plan.application.service.PlanService;
import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDetailsResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanDetailUseCase {

  private static final Logger log = LoggerFactory.getLogger(PlanDetailUseCase.class);

  private final PlanService planService;
  private final FeatureService featureService;

  public List<PlanDetailsResponseDto> getAll() {
    log.debug("Use case: Retrieving details for all plans");
    List<Plan> plans = planService.getAll();
    List<PlanDetailsResponseDto> responseDtos = new ArrayList<>();

    for (Plan plan : plans) {
      List<Feature> features = featureService.getAllByPlan(plan.getId().id().toString());
      responseDtos.add(PlanDetailsResponseDto.fromPlan(plan, features));
    }

    log.info("Retrieved details for {} plans", responseDtos.size());
    return responseDtos;
  }

  public List<PlanDetailsResponseDto> getAllByTenantId(String id) {
    log.debug("Use case: Retrieving details for all plans");
    List<Plan> plans = planService.getAllByTenant(id);
    List<PlanDetailsResponseDto> responseDtos = new ArrayList<>();

    for (Plan plan : plans) {
      List<Feature> features = featureService.getAllByPlan(plan.getId().id().toString());
      responseDtos.add(PlanDetailsResponseDto.fromPlan(plan, features));
    }

    log.info("Retrieved details for {} plans", responseDtos.size());
    return responseDtos;
  }

  public PlanDetailsResponseDto getById(String id) {
    log.debug("Use case: Retrieving plan details by ID: {}", id);
    Plan plan = planService.getById(id);
    List<Feature> features = featureService.getAllByPlan(id);
    PlanDetailsResponseDto dto = PlanDetailsResponseDto.fromPlan(plan, features);
    log.info("Plan details retrieved for ID: {}", id);
    return dto;
  }

}
