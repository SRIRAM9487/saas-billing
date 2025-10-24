package com.saas.billing_system.user.infrastructure.controller.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/ui")
public class UserUiController {

  private final Logger log = LoggerFactory.getLogger(UserUiController.class);

  @GetMapping("/register")
  public String registerUser(Model model) {
    log.trace("User register ui is requested");
    model.addAttribute("message", "hello world");
    return "user/register";
  }

  @GetMapping("/login")
  public String loginUser(Model model) {
    model.addAttribute("messsage", "hello world");
    return "user/login";
  }
}
