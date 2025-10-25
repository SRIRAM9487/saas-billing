package com.saas.billing_system.plan.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.vo.FeatureId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, FeatureId> {

  Optional<Feature> findByPlan(UUID plan);

  List<Feature> findAllByPlan(UUID plan);
}
