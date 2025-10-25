package com.saas.billing_system.plan.application.service;

import java.util.List;

import com.saas.billing_system.plan.domain.entity.Feature;
import com.saas.billing_system.plan.domain.exception.FeatureException;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.persistence.FeatureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private static final Logger log = LoggerFactory.getLogger(FeatureService.class);

    private final FeatureRepository featureRepository;
    private final FeatureFactory featureFactory;

    public List<Feature> getAll() {
        log.debug("Retrieving all features");
        List<Feature> features = featureFactory.getAll();
        if (features == null || features.isEmpty()) {
            log.warn("No features found");
            throw FeatureException.notFound();
        }
        log.info("Retrieved {} features", features.size());
        return features;
    }

    public Feature getById(String id) {
        log.debug("Retrieving feature by ID: {}", id);
        Feature feature = featureFactory.getById(id);
        log.info("Feature retrieved: {}", feature.getId());
        return feature;
    }

    public Feature getByPlan(String id) {
        log.debug("Retrieving feature by plan ID: {}", id);
        Feature feature = featureFactory.getByPlan(id);
        log.info("Feature retrieved for plan: {}", id);
        return feature;
    }

    public List<Feature> getAllByPlan(String id) {
        log.debug("Retrieving all features for plan ID: {}", id);
        List<Feature> features = featureFactory.getAllByPlan(id);
        if (features == null || features.isEmpty()) {
            log.warn("No features found for plan ID: {}", id);
            throw FeatureException.notFound();
        }
        log.info("Retrieved {} features for plan ID: {}", features.size(), id);
        return features;
    }

    public Feature create(FeatureCreateRequestDto requestDto) {
        log.debug("Creating new feature: {}", requestDto);
        Feature feature = FeatureCreateRequestDto.toFeature(requestDto);
        Feature savedFeature = featureRepository.save(feature);
        log.info("Feature created successfully with ID: {}", savedFeature.getId());
        return savedFeature;
    }

    public List<Feature> create(String planId, List<FeatureCreateRequestDto> requestDto) {
        log.debug("Creating {} features for plan ID: {}", requestDto.size(), planId);
        List<Feature> features = requestDto.stream()
                .map(dto -> FeatureCreateRequestDto.toFeature(planId, dto))
                .toList();
        List<Feature> savedFeatures = featureRepository.saveAll(features);
        log.info("Created {} features for plan ID: {}", savedFeatures.size(), planId);
        return savedFeatures;
    }

    public Feature update(String featureId, FeatureCreateRequestDto requestDto) {
        log.debug("Updating feature ID: {} with data: {}", featureId, requestDto);
        Feature feature = featureFactory.getById(featureId);
        feature.setName(requestDto.name());
        feature.setDescription(requestDto.description());
        feature.setRateLimit(requestDto.rateLimit());
        feature.setPlan(requestDto.plan());
        Feature updatedFeature = featureRepository.save(feature);
        log.info("Feature updated successfully: {}", updatedFeature.getId());
        return updatedFeature;
    }

    public Feature update(FeatureUpdateRequestDto requestDto) {
        log.debug("Updating feature via DTO: {}", requestDto.id());
        Feature feature = featureFactory.getById(requestDto.id());
        feature.setName(requestDto.name());
        feature.setDescription(requestDto.description());
        feature.setRateLimit(requestDto.rateLimit());
        feature.setPlan(requestDto.plan());
        Feature updatedFeature = featureRepository.save(feature);
        log.info("Feature updated via DTO: {}", updatedFeature.getId());
        return updatedFeature;
    }

    public List<Feature> update(String planId, List<FeatureCreateRequestDto> requestDto) {
        log.debug("Bulk updating features for plan ID: {}", planId);
        List<Feature> features = this.getAllByPlan(planId);
        // Note: This method seems incomplete — consider mapping requestDto to existing features
        List<Feature> updatedFeatures = featureRepository.saveAll(features);
        log.info("Bulk updated {} features for plan ID: {}", updatedFeatures.size(), planId);
        return updatedFeatures;
    }

    public Feature delete(String featureId) {
        log.debug("Soft deleting feature ID: {}", featureId);
        Feature feature = featureFactory.getById(featureId);
        feature.softDelete();
        Feature deletedFeature = featureRepository.save(feature);
        log.info("Feature soft deleted: {}", featureId);
        return deletedFeature;
    }

    public List<Feature> deleteByPlanId(String planId) {
        log.debug("Soft deleting all features for plan ID: {}", planId);
        List<Feature> features = featureFactory.getAllByPlan(planId);
        features.forEach(Feature::softDelete);
        List<Feature> deletedFeatures = featureRepository.saveAll(features);
        log.info("Soft deleted {} features for plan ID: {}", deletedFeatures.size(), planId);
        return deletedFeatures;
    }
}
