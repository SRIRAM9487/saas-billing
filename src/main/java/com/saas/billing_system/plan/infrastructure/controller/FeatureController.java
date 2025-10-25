package com.saas.billing_system.plan.infrastructure.controller;

import java.util.List;

import com.saas.billing_system.plan.application.usecase.FeatureCreationUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureDeleteUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureDetailsUseCase;
import com.saas.billing_system.plan.application.usecase.FeatureUpdateUseCase;
import com.saas.billing_system.plan.infrastructure.dto.request.feature.FeatureCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureCreateResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.feature.FeatureDetailsResponseDto;
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

  private final Logger log = LoggerFactory.getLogger(FeatureController.class);
  private final FeatureCreationUseCase creationUseCase;
  private final FeatureUpdateUseCase updateUseCase;
  private final FeatureDeleteUseCase deleteUseCase;
  private final FeatureDetailsUseCase detailsUseCase;

  @GetMapping("/all")
  public ResponseEntity<ApiResponseDto<List<FeatureDetailsResponseDto>>> getAll() {

    ApiResponseDto<List<FeatureDetailsResponseDto>> response = ApiResponseDto.create(detailsUseCase.getAll());

    return ResponseEntity.ok(response);
  }

  @GetMapping("/plan/{planId}")
  public ResponseEntity<ApiResponseDto<List<FeatureDetailsResponseDto>>> getByPlanId(
      @PathVariable("planId") String planId) {
    ApiResponseDto<List<FeatureDetailsResponseDto>> response = ApiResponseDto
        .create(detailsUseCase.getByPlanId(planId));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponseDto<FeatureCreateResponseDto>> create(
      @RequestBody FeatureCreateRequestDto requestDto) {
    ApiResponseDto<FeatureCreateResponseDto> response = ApiResponseDto.create(creationUseCase.create(requestDto));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{featureId}")
  public ResponseEntity<ApiResponseDto<FeatureCreateResponseDto>> update(
      @PathVariable("featureId") String featureId,
      @RequestBody FeatureCreateRequestDto requestDto) {
    ApiResponseDto<FeatureCreateResponseDto> response = null;
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{featureId}")
  public ResponseEntity<ApiResponseDto<FeatureCreateResponseDto>> delete(
      @RequestBody FeatureCreateRequestDto requestDto) {
    ApiResponseDto<FeatureCreateResponseDto> response = null;
    return ResponseEntity.ok(response);

  }
}
