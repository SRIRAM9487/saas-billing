package com.saas.billing_system.shared.security.web.handler;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.billing_system.shared.context.UserContextHolder;
import com.saas.billing_system.shared.dto.response.ApiExceptionDto;
import com.saas.billing_system.shared.exception.UnAuthorizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerException implements AccessDeniedHandler {

  private final Logger log = LoggerFactory.getLogger(AccessDeniedHandlerException.class);

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.debug("Access Denied");

    log.trace("User Id {}", UserContextHolder.getUserId());
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json");
    ApiExceptionDto exception = ApiExceptionDto.unAuthorized(UnAuthorizedException.create());

    new ObjectMapper().writeValue(response.getWriter(), exception);
    log.trace(exception.toString());
  }

}
