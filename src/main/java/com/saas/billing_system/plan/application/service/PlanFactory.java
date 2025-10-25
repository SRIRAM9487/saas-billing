package com.saas.billing_system.plan.application.service;

import java.util.List;
import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.domain.exception.PlanException;
import com.saas.billing_system.plan.domain.vo.PlanId;
import com.saas.billing_system.plan.infrastructure.persistence.PlanRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanFactory {

  public final PlanRepository planRepo;

  public List<Plan> getAll() {
    List<Plan> plans = planRepo.findAll();
    return plans;
  }

  public Plan getById(String id) {
    return planRepo.findById(PlanId.create(id)).orElseThrow(() -> {
      return PlanException.notFound(id);
    });
  }

  public Plan getByTenant(String id) {
    return planRepo.findByTenant(UUID.fromString(id)).orElseThrow(() -> {
      return PlanException.notFound(id);
    });
  }

  public List<Plan> getAllByTenant(String id) {
    return planRepo.findAllByTenant(UUID.fromString(id));
  }
}
