package com.saas.billing_system.shared.security.web.handler;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.dto.response.ApiExceptionDto;
import com.saas.billing_system.shared.exception.UnAuthenticatedException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointException implements AuthenticationEntryPoint {

  private String type;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    type = "application/json";
    response.setContentType(type);

    if (UserContextHolder.getUserId() != null) {
      new ObjectMapper().writeValue(response.getWriter(), ApiExceptionDto.create(UnAuthenticatedException.create()));
    } else {
      new ObjectMapper().writeValue(response.getWriter(),
          ApiExceptionDto.unAuthorized(UnAuthenticatedException.userIdMissing()));
    }
  }

}
