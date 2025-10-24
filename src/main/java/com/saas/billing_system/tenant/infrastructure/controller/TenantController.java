package com.saas.billing_system.tenant.infrastructure.controller;

import com.saas.billing_system.shared.dto.response.ApiResponseDto;
import com.saas.billing_system.tenant.application.usecase.TenantRegistrationUsecase;
import com.saas.billing_system.tenant.infrastructure.dto.request.TenantCreateRequestDto;
import com.saas.billing_system.tenant.infrastructure.dto.response.TenantCreateResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tenant")
public class TenantController {

  private final TenantRegistrationUsecase registrationUsecase;

  @PostMapping("/create")
  public ResponseEntity<ApiResponseDto<TenantCreateResponseDto>> register(
      @RequestBody TenantCreateRequestDto requestDto) {
    ApiResponseDto<TenantCreateResponseDto> response = ApiResponseDto.create(registrationUsecase.register(requestDto));
    return ResponseEntity.ok(response);
  }

}
