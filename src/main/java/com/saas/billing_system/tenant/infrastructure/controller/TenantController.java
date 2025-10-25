package com.saas.billing_system.tenant.infrastructure.controller;

import java.util.List;
import java.util.UUID;

import com.saas.billing_system.shared.dto.response.ApiResponseDto;
import com.saas.billing_system.tenant.application.usecase.TenantDeleteUseCase;
import com.saas.billing_system.tenant.application.usecase.TenantDetailsUseCase;
import com.saas.billing_system.tenant.application.usecase.TenantRegistrationUsecase;
import com.saas.billing_system.tenant.application.usecase.TenantUpdateUseCase;
import com.saas.billing_system.tenant.domain.entity.Tenant;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantUpdateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantCreateResponseDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantDeleteResponsetDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantDetailsResponseDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantUpdateResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tenant")
public class TenantController {

  private static final Logger log = LoggerFactory.getLogger(TenantController.class);

  private final TenantRegistrationUsecase registrationUsecase;
  private final TenantUpdateUseCase updateUseCase;
  private final TenantDeleteUseCase deleteUseCase;
  private final TenantDetailsUseCase detailsUseCase;

  @GetMapping("/all")
  public ResponseEntity<ApiResponseDto<List<TenantDetailsResponseDto>>> getAll() {
    log.debug("Fetching all tenants via controller");

    List<TenantDetailsResponseDto> dtoList = detailsUseCase.getAll();
    ApiResponseDto<List<TenantDetailsResponseDto>> response = ApiResponseDto.create(dtoList);
    log.debug("Returning {} tenants", dtoList.size());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponseDto<TenantDetailsResponseDto>> getById(@PathVariable("userId") String userId) {
    log.debug("Fetching all tenants via controller");

    TenantDetailsResponseDto dtoList = detailsUseCase.getByUserId(userId);
    ApiResponseDto<TenantDetailsResponseDto> response = ApiResponseDto.create(dtoList);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponseDto<TenantCreateResponseDto>> register(
      @RequestBody TenantCreateRequestDto requestDto) {
    log.info("Request to create tenant for user: {}", requestDto.userRequestDto().email());
    ApiResponseDto<TenantCreateResponseDto> response = ApiResponseDto.create(registrationUsecase.register(requestDto));
    log.info("Tenant created successfully for user: {}", requestDto.userRequestDto().email());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/update/{userId}")
  public ResponseEntity<ApiResponseDto<TenantUpdateResponseDto>> update(
      @PathVariable("userId") String userId,
      @RequestBody TenantUpdateRequestDto requestDto) {
    log.info("Request to update tenant for userId: {}", userId);
    ApiResponseDto<TenantUpdateResponseDto> response = ApiResponseDto.create(updateUseCase.update(userId, requestDto));
    log.info("Tenant updated successfully for userId: {}", userId);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<ApiResponseDto<TenantDeleteResponsetDto>> delete(@PathVariable("userId") String userId) {
    log.info("Request to delete tenant for userId: {}", userId);
    ApiResponseDto<TenantDeleteResponsetDto> response = ApiResponseDto.create(deleteUseCase.delete(userId));
    log.info("Tenant deleted successfully for userId: {}", userId);
    return ResponseEntity.ok(response);
  }

}
