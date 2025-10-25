package com.saas.billing_system.plan.application.service;

import java.util.ArrayList;
import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.exception.FeatureException;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.persistence.FeatureRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureService {

  private final FeatureRepository featureRepository;
  private final FeatureFactory featureFactory;

  public List<Feature> getAll() {
    List<Feature> features = featureFactory.getAll();
    if (features == null) {
      throw FeatureException.notFound();
    }
    return features;
  }

  public Feature getById(String id) {
    Feature features = featureFactory.getById(id);
    return features;
  }

  public Feature getByPlan(String id) {
    Feature features = featureFactory.getByPlan(id);
    return features;
  }

  public List<Feature> getAllByPlan(String id) {
    List<Feature> features = featureFactory.getAllByPlan(id);
    if (features == null) {
      throw FeatureException.notFound();
    }
    return features;
  }

  public Feature create(FeatureCreateRequestDto requestDto) {
    Feature feature = FeatureCreateRequestDto.toFeature(requestDto);
    Feature savedFeature = featureRepository.save(feature);
    return savedFeature;
  }

  public List<Feature> create(String planId, List<FeatureCreateRequestDto> requestDto) {
    List<Feature> features = requestDto
        .stream()
        .map(feature -> FeatureCreateRequestDto.toFeature(planId, feature))
        .toList();
    List<Feature> savedFeature = featureRepository.saveAll(features);
    return savedFeature;
  }

  public Feature update(String featureId, FeatureCreateRequestDto requestDto) {
    Feature feature = featureFactory.getById(featureId);
    feature.setName(requestDto.name());
    feature.setDescription(requestDto.description());
    feature.setRateLimit(requestDto.rateLimit());
    feature.setPlan(requestDto.plan());
    Feature updatedFeature = featureRepository.save(feature);
    return updatedFeature;
  }

  public Feature update(FeatureUpdateRequestDto requestDto) {
    Feature feature = featureFactory.getById(requestDto.id());
    feature.setName(requestDto.name());
    feature.setDescription(requestDto.description());
    feature.setRateLimit(requestDto.rateLimit());
    feature.setPlan(requestDto.plan());
    Feature updatedFeature = featureRepository.save(feature);
    return updatedFeature;
  }

  public List<Feature> update(String planId, List<FeatureCreateRequestDto> requestDto) {

    List<Feature> features = this.getAllByPlan(planId);

    List<Feature> updatedFeature = featureRepository.saveAll(features);
    return updatedFeature;
  }

  public Feature delete(String featureId) {
    Feature deleteFeature = featureFactory.getById(featureId);
    deleteFeature.softDelete();
    Feature feature = featureRepository.save(deleteFeature);
    return feature;
  }

  public List<Feature> deleteByPlanId(String planId) {
    List<Feature> deleteFeature = featureFactory.getAllByPlan(planId);
    deleteFeature.forEach(feat -> feat.softDelete());
    List<Feature> features = featureRepository.saveAll(deleteFeature);
    return features;
  }

}
