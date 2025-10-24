package com.saas.billing_system.shared.security.web.filter;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.billing_system.shared.dto.response.ApiExceptionDto;
import com.saas.billing_system.user.application.service.JwtService;
import com.saas.billing_system.user.application.service.UserDetailsServiceImpl;
import com.saas.billing_system.user.domain.entity.UserImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(JwtFilter.class);
  private final UserDetailsServiceImpl userDetailsServiceImp;
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    log.debug("Invoking custom Jwt Authentication Filter");

    String path = request.getRequestURI();
    log.trace("Request Path : {}", path);

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String userName = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      log.error("Authorization Header is present");
      token = authHeader.substring(7);
      userName = jwtService.extractUserId(token);
    }

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      log.trace("User is authenticated");
      UserImpl user = (UserImpl) userDetailsServiceImp.loadUserByUsername(userName);
      if (jwtService.validate(token, user.getId())) {
        log.trace("User is authenticated");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
            user.getAuthorities());
        log.trace("userName Password Authentication Token is set");
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.trace("Security Context is set");
      }
    }

    log.trace(" Requested User id : {}", userName);

    filterChain.doFilter(request, response);
  }

}
