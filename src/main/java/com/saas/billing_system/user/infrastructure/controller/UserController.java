package com.saas.billing_system.user.infrastructure.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  @Value("${server.port}")
  private String port;

  @GetMapping(path = "/hello")
  public String server() {
    System.out.println("THE SERVER IS RUNNING");
    return "Server is running in " + port;
  }

}
