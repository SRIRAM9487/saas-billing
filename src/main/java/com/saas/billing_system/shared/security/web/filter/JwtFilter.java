package com.saas.billing_system.shared.security.web.filter;

import java.io.IOException;

import com.saas.billing_system.user.application.service.JwtService;
import com.saas.billing_system.user.application.service.UserDetailsServiceImpl;
import com.saas.billing_system.user.domain.entity.UserImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);
  private final UserDetailsServiceImpl userDetailsServiceImp;
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    LOG.debug("Invoking custom Jwt Authentication Filter");

    String path = request.getRequestURI();
    LOG.trace("Request Path : {}", path);

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String userName = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      LOG.error("Authorization Header is present");
      authHeader.substring(7);
      userName = jwtService.extractUserId(token);

    }

    try {

      if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserImpl user = (UserImpl) userDetailsServiceImp.loadUserByUsername(userName);
        if (jwtService.validate(token, user.getId())) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
              user.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (ExpiredJwtException e) {
      LOG.warn("Token is expired: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      LOG.warn("Malformed JWT token: {}", e.getMessage());

    } catch (SecurityException e) {
      LOG.warn("Signature validation failed: {}", e.getMessage());

    } catch (UnsupportedJwtException e) {
      LOG.warn("Unsupported JWT token: {}", e.getMessage());

    } catch (IllegalArgumentException e) {
      LOG.warn("Empty or null JWT token: {}", e.getMessage());

    } catch (JwtException e) {
      LOG.warn("General JWT error: {}", e.getMessage());

    } catch (NullPointerException e) {
      LOG.error("Missing required claim: {}", e.getMessage());

    } catch (Exception e) {
      LOG.error("Unexpected Error: {}", e.getMessage(), e);
    }

    LOG.trace(" Requested User id : {}", userName);

    filterChain.doFilter(request, response);
  }

}
