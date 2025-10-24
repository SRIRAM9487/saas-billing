package com.saas.billing_system.shared.security.web.filter;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.billing_system.shared.dto.response.ApiExceptionDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

@Component
public class ExceptionalHandlerFilter extends OncePerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(ExceptionalHandlerFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException e) {
      log.warn("Token is expired: {}", e.getMessage());
      writeException("Token Expired", response);
    } catch (MalformedJwtException e) {
      log.warn("Malformed JWT token: {}", e.getMessage());
      writeException("Malformed Token", response);
    } catch (SecurityException e) {
      log.warn("Signature validation failed: {}", e.getMessage());
      writeException("Signature validation failed", response);
    } catch (UnsupportedJwtException e) {
      log.warn("Unsupported JWT token: {}", e.getMessage());
      writeException("Unsupported jwt", response);
    } catch (IllegalArgumentException e) {
      log.warn("Empty or null JWT token: {}", e.getMessage());
      writeException("Null token", response);
    } catch (JwtException e) {
      log.warn("General JWT error: {}", e.getMessage());
      writeException("Invalid Token", response);
    } catch (NullPointerException e) {
      log.error("Missing required claim: {}", e.getMessage());
      writeException("Missing claims", response);
    }
  }

  public void writeException(String value, HttpServletResponse response)
      throws StreamWriteException, DatabindException, IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    new ObjectMapper().writeValue(response.getWriter(), ApiExceptionDto.jwt(value));
  }

}
