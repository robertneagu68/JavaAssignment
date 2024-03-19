package com.example.interviewskeleton.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
              .csrf(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                .requestMatchers("/greet/**").authenticated()
                .anyRequest().permitAll())
              .addFilterBefore(new CustomAuthFilter(), UsernamePasswordAuthenticationFilter.class)
      .build();
  }
}

