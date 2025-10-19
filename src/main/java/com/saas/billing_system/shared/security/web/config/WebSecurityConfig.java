package com.saas.billing_system.shared.security.web.config;

import com.saas.billing_system.shared.constant.RequestConstant;
import com.saas.billing_system.shared.security.web.handler.AccessDeniedHandlerException;
import com.saas.billing_system.shared.security.web.handler.AuthenticationEntryPointException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final AccessDeniedHandlerException accessDeniedHandlerException;
  private final AuthenticationEntryPointException authenticationEntryPoint;

  @Bean
  public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf(csrf -> csrf.disable())
        .logout(logout -> logout.disable())
        .formLogin(login -> login.disable())
        .httpBasic(basic -> basic.disable())
        .cors(cors -> cors.disable());

    httpSecurity.exceptionHandling(exception -> exception
        .accessDeniedPage("accessdenied.html")
        .accessDeniedHandler(accessDeniedHandlerException)
        .authenticationEntryPoint(authenticationEntryPoint));

    httpSecurity.authorizeHttpRequests(http -> http
        .requestMatchers(HttpMethod.GET, RequestConstant.allowedGetPath)
        .permitAll()
        .requestMatchers(HttpMethod.POST, RequestConstant.loginPath)
        .permitAll()
        .anyRequest()
        .authenticated());

    return httpSecurity.build();
  }

}
