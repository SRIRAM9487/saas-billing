package com.saas.billing_system.shared.security.web.filter;

import java.io.IOException;
import java.util.UUID;

import com.saas.billing_system.shared.context.UserContext;
import com.saas.billing_system.shared.context.UserContextHolder;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserContextFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String userId = request.getHeader("userId");
    UserContext userContext = UserContext
        .builder()
        .path(request.getRequestURI())
        .method(HttpMethod.valueOf(request.getMethod()))
        .build();

    if (userId != null && !userId.isEmpty())
      userContext.setUserId(UUID.fromString(userId));

    UserContextHolder.set(userContext);
    filterChain.doFilter(request, response);
  }

}
