package com.saas.billing_system.plan.application.service;

import java.util.List;
import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.exception.FeatureException;
import com.saas.billing_system.plan.domain.vo.FeatureId;
import com.saas.billing_system.plan.infrastructure.persistence.FeatureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureFactory {

  private static final Logger log = LoggerFactory.getLogger(FeatureFactory.class);

  public final FeatureRepository featureRepository;

  public Feature getById(String id) {
    log.debug("Fetching feature by ID: {}", id);
    return featureRepository.findById(FeatureId.create(id))
        .orElseThrow(() -> {
          log.warn("Feature not found with ID: {}", id);
          return FeatureException.notFound(id);
        });
  }

  public List<Feature> getAllByPlan(String id) {
    log.debug("Fetching all features for plan ID: {}", id);
    return featureRepository.findAllByPlan(UUID.fromString(id));
  }

  public Feature getByPlan(String id) {
    log.debug("Fetching feature by plan ID: {}", id);
    return featureRepository.findByPlan(UUID.fromString(id))
        .orElseThrow(() -> {
          log.warn("No feature found for plan ID: {}", id);
          return FeatureException.notFound(id);
        });
  }

  public List<Feature> getAll() {
    log.debug("Fetching all features");
    return featureRepository.findAll();
  }
}
