package com.saas.billing_system.plan.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Plan;
import com.saas.billing_system.plan.domain.vo.PlanId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,PlanId>{

  Optional<Plan> findByTenant(UUID id);

  List<Plan> findAllByTenant(UUID id);
  
}
