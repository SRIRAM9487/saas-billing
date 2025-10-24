package com.saas.billing_system.tenant.application.usecase;

import org.springframework.stereotype.Service;

@Service
public class ApiGeneratorUseCase {


  public String generateApi(String userId){
    return "user";
  }
  
}
