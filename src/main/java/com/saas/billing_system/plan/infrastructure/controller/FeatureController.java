package com.saas.billing_system.plan.infrastructure.controller;

import java.util.List;

import com.saas.billing_system.plan.application.usecase.FeatureCreationUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureDeleteUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureDetailsUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureUpdateUseCase;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureCreateResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDeleteResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDetailsResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureUpdateResponseDto;
import com.saas.billing_system.shared.dto.response.ApiResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature")
public class FeatureController {

  private static final Logger log = LoggerFactory.getLogger(FeatureController.class);

  private final FeatureCreationUseCase creationUseCase;
  private final FeatureUpdateUseCase updateUseCase;
  private final FeatureDeleteUseCase deleteUseCase;
  private final FeatureDetailsUseCase detailsUseCase;

  @GetMapping("/all")
  public ResponseEntity<ApiResponseDto<List<FeatureDetailsResponseDto>>> getAll() {
    log.debug("Fetching all features via controller");
    List<FeatureDetailsResponseDto> dtoList = detailsUseCase.getAll();
    ApiResponseDto<List<FeatureDetailsResponseDto>> response = ApiResponseDto.create(dtoList);
    log.info("Returning {} features", dtoList.size());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/plan/{planId}")
  public ResponseEntity<ApiResponseDto<List<FeatureDetailsResponseDto>>> getByPlanId(
      @PathVariable("planId") String planId) {
    log.debug("Fetching features for plan ID: {}", planId);
    List<FeatureDetailsResponseDto> dtoList = detailsUseCase.getByPlanId(planId);
    ApiResponseDto<List<FeatureDetailsResponseDto>> response = ApiResponseDto.create(dtoList);
    log.info("Returning {} features for plan ID: {}", dtoList.size(), planId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponseDto<FeatureCreateResponseDto>> create(
      @RequestBody FeatureCreateRequestDto requestDto) {
    log.info("Request to create feature: {}", requestDto.name());
    FeatureCreateResponseDto result = creationUseCase.create(requestDto);
    ApiResponseDto<FeatureCreateResponseDto> response = ApiResponseDto.create(result);
    log.info("Feature created successfully with ID ");
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{featureId}")
  public ResponseEntity<ApiResponseDto<FeatureUpdateResponseDto>> update(
      @PathVariable("featureId") String featureId,
      @RequestBody FeatureCreateRequestDto requestDto) {
    log.info("Request to update feature ID: {}", featureId);
    FeatureUpdateResponseDto result = updateUseCase.update(featureId, requestDto);
    ApiResponseDto<FeatureUpdateResponseDto> response = ApiResponseDto.create(result);
    log.info("Feature updated successfully: {}", featureId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{featureId}")
  public ResponseEntity<ApiResponseDto<FeatureDeleteResponseDto>> delete(
      @PathVariable("featureId") String featureId) {
    log.info("Request to delete feature ID: {}", featureId);
    FeatureDeleteResponseDto result = deleteUseCase.delete(featureId);
    ApiResponseDto<FeatureDeleteResponseDto> response = ApiResponseDto.create(result);
    log.info("Feature deleted successfully: {}", featureId);
    return ResponseEntity.ok(response);
  }
}
