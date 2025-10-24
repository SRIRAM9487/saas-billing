package com.saas.billing_system.shared.security.web.config;

import com.saas.billing_system.shared.constant.RequestConstant;
import com.saas.billing_system.shared.security.web.filter.ExceptionalHandlerFilter;
import com.saas.billing_system.shared.security.web.filter.JwtFilter;
import com.saas.billing_system.shared.security.web.filter.UserContextFilter;
import com.saas.billing_system.shared.security.web.handler.AccessDeniedHandlerException;
import com.saas.billing_system.shared.security.web.handler.AuthenticationEntryPointException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final AccessDeniedHandlerException accessDeniedHandlerException;
  private final AuthenticationEntryPointException authenticationEntryPoint;
  private final UserContextFilter userContextFilter;
  private final JwtFilter jwtFilter;
  private final ExceptionalHandlerFilter exceptionalHandlerFilter;

  @Bean
  public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf(csrf -> csrf.disable())
        .logout(logout -> logout.disable())
        .formLogin(login -> login.disable())
        .httpBasic(basic -> basic.disable())
        .cors(cors -> cors.disable());

    httpSecurity.exceptionHandling(exception -> exception
        // .accessDeniedPage("/access_denied.html")
        .accessDeniedHandler(accessDeniedHandlerException)
        .authenticationEntryPoint(authenticationEntryPoint));

    httpSecurity.authorizeHttpRequests(http -> http
        .requestMatchers(HttpMethod.GET, RequestConstant.getPaths)
        .permitAll()
        .requestMatchers(HttpMethod.POST, RequestConstant.postPaths)
        .permitAll()
        .requestMatchers(HttpMethod.PATCH, RequestConstant.patchPaths)
        .permitAll()
        .anyRequest()
        .authenticated());

    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    httpSecurity.addFilterBefore(userContextFilter, JwtFilter.class);
    httpSecurity.addFilterBefore(exceptionalHandlerFilter, UserContextFilter.class);

    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
