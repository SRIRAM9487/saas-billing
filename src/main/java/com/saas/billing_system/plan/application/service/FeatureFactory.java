package com.saas.billing_system.plan.application.service;

import java.util.List;
import java.util.UUID;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.exception.FeatureException;
import com.saas.billing_system.plan.domain.vo.FeatureId;
import com.saas.billing_system.plan.infrastructure.persistence.FeatureRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureFactory {

  public final FeatureRepository featureRepository;

  public Feature getById(String id) {
    return featureRepository.findById(FeatureId.create(id)).orElseThrow(() -> {
      return FeatureException.notFound(id);
    });
  }

  public List<Feature> getAllByPlan(String id) {
    return featureRepository.findAllByPlan(UUID.fromString(id));
  }

  public Feature getByPlan(String id) {
    return featureRepository.findByPlan(UUID.fromString(id)).orElseThrow(() -> {
      return FeatureException.notFound(id);
    });
  }

  public List<Feature> getAll() {
    return featureRepository.findAll();
  }

}
