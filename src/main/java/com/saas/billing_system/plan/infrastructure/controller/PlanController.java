package com.saas.billing_system.plan.infrastructure.controller;

import java.util.List;

import com.saas.billing_system.plan.application.usecase.PlanCreateUseCase;
import com.saas.billing_system.plan.application.usecase.PlanDeleteUseCase;
import com.saas.billing_system.plan.application.usecase.PlanDetailUseCase;
import com.saas.billing_system.plan.application.usecase.PlanUpdateUseCase;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanCreateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.request.plan.PlanUpdateRequestDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanCreateResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDeleteResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanDetailsResponseDto;
import com.saas.billing_system.plan.infrastructure.dto.response.plan.PlanUpdateResponseDto;
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
@RequestMapping("/plan")
public class PlanController {

  private static final Logger log = LoggerFactory.getLogger(PlanController.class);

  private final PlanCreateUseCase createUseCase;
  private final PlanUpdateUseCase updateUseCase;
  private final PlanDeleteUseCase deleteUseCase;
  private final PlanDetailUseCase detailUseCase;

  @GetMapping("/all")
  public ResponseEntity<ApiResponseDto<List<PlanDetailsResponseDto>>> getAll() {
    log.debug("Fetching all plans via controller");
    List<PlanDetailsResponseDto> dtoList = detailUseCase.getAll();
    ApiResponseDto<List<PlanDetailsResponseDto>> response = ApiResponseDto.create(dtoList);
    log.info("Returning {} plans", dtoList.size());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/all/{tenantId}")
  public ResponseEntity<ApiResponseDto<List<PlanDetailsResponseDto>>> getAllTenantById(
      @PathVariable("tenantId") String id) {
    log.debug("Fetching all plans via controller");
    List<PlanDetailsResponseDto> dtoList = detailUseCase.getAllByTenantId(id);
    ApiResponseDto<List<PlanDetailsResponseDto>> response = ApiResponseDto.create(dtoList);
    log.info("Returning {} plans", dtoList.size());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{planId}")
  public ResponseEntity<ApiResponseDto<PlanDetailsResponseDto>> getById(
      @PathVariable("planId") String planId) {
    log.debug("Fetching plan by ID: {}", planId);
    PlanDetailsResponseDto dto = detailUseCase.getById(planId);
    ApiResponseDto<PlanDetailsResponseDto> response = ApiResponseDto.create(dto);
    log.info("Plan retrieved: {}", planId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create/{tenantId}")
  public ResponseEntity<ApiResponseDto<PlanCreateResponseDto>> create(
      @PathVariable("tenantId") String tenantId,
      @RequestBody PlanCreateRequestDto requestDto) {
    log.info("Request to create plan for tenant ID: {}", tenantId);
    PlanCreateResponseDto result = createUseCase.create(tenantId, requestDto);
    ApiResponseDto<PlanCreateResponseDto> response = ApiResponseDto.create(result);
    log.info("Plan created successfully with ID");
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{planId}")
  public ResponseEntity<ApiResponseDto<PlanUpdateResponseDto>> update(
      @PathVariable("planId") String planId,
      @RequestBody PlanUpdateRequestDto requestDto) {
    log.info("Request to update plan ID: {}", planId);
    PlanUpdateResponseDto result = updateUseCase.planUpdateUseCase(planId, requestDto);
    ApiResponseDto<PlanUpdateResponseDto> response = ApiResponseDto.create(result);
    log.info("Plan updated successfully: {}", planId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{planId}")
  public ResponseEntity<ApiResponseDto<PlanDeleteResponseDto>> delete(
      @PathVariable("planId") String planId) {
    log.info("Request to delete plan ID: {}", planId);
    PlanDeleteResponseDto result = deleteUseCase.delete(planId);
    ApiResponseDto<PlanDeleteResponseDto> response = ApiResponseDto.create(result);
    log.info("Plan deleted successfully: {}", planId);
    return ResponseEntity.ok(response);
  }
}
