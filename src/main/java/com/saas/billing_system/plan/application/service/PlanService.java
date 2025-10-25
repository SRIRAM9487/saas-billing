package com.saas.billing_system.plan.application.service;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.domain.exception.PlanException;
import com.saas.billing_system.plan.domain.vo.PlanQuality;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.persistence.PlanRepository;
import com.saas.billing_system.tenant.domain.vo.DefaultCurrency;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

  private final PlanRepository planRepository;
  private final PlanFactory planFactory;

  public List<Plan> getAll() {
    List<Plan> plans = planFactory.getAll();

    if (plans == null || plans.size() == 0)
      throw PlanException.notFound();

    return plans;
  }

  public Plan getById(String id) {
    Plan plan = planFactory.getById(id);
    return plan;
  }

  public List<Plan> getAllByTenant(String id) {
    List<Plan> plans = planFactory.getAllByTenant(id);

    if (plans == null || plans.size() == 0)
      throw PlanException.notFound();

    return plans;
  }

  public Plan create(String tenantId, PlanCreateRequestDto requestDto) {
    Plan plan = PlanCreateRequestDto.create(tenantId, requestDto);
    Plan savedPlan = planRepository.save(plan);
    return savedPlan;
  }

  public Plan update(String planId, PlanUpdateRequestDto requestDto) {
    Plan plan = planFactory.getById(planId);
    plan.setName(requestDto.name());
    plan.setDescription(requestDto.description());
    plan.setDefaultCurrency(DefaultCurrency.valueOf(requestDto.defaultCurrency()));
    plan.setBasePrice(requestDto.basePrice());
    plan.setTaxPrice(requestDto.taxPrice());
    plan.setTaxIncluded(requestDto.taxIncluded());
    plan.setDurationInDays(requestDto.durationInDays());
    plan.setQuality(PlanQuality.valueOf(requestDto.quality()));
    Plan savedPlan = planRepository.save(plan);
    return savedPlan;
  }

  public Plan delete(String planId) {
    Plan plan = planFactory.getById(planId);
    Plan deletedPlan = planRepository.save(plan);
    return deletedPlan;
  }
  public Plan deleteByPlanId(String planId) {
    Plan plan = planFactory.getById(planId);
    Plan deletedPlan = planRepository.save(plan);
    return deletedPlan;
  }
}
