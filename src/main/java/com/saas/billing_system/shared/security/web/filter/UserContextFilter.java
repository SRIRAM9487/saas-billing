package com.saas.billing_system.shared.security.web.filter;

import java.io.IOException;
import java.util.UUID;

import com.saas.billing_system.shared.context.UserContext;
import com.saas.billing_system.shared.context.UserContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserContextFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(UserContextFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    log.debug("invoking User Context Filter");

    String userId = request.getHeader("userId");

    UserContext userContext = UserContext
        .builder()
        .path(request.getRequestURI())
        .method(HttpMethod.valueOf(request.getMethod()))
        .build();

    log.trace("user Id : {}", userId);
    if (userId != null && !userId.isEmpty())
      userContext.setUserId(UUID.fromString(userId));

    UserContextHolder.set(userContext);
    filterChain.doFilter(request, response);
  }

}
