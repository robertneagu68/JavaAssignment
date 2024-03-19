package com.example.interviewskeleton.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomAuthFilter extends OncePerRequestFilter {
  private final static String Auth_Token_Header = "X-Auth-Token";

  private final static String Auth_Token_Value = "such-secure-much-wow";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    String authToken = request.getHeader(Auth_Token_Header);
    System.out.println("Auth Token Received: " + authToken);

    if (Auth_Token_Value.equals(authToken)) {
      Authentication authentication = new TokenAuth(authToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write("Access Denied");
    }
  }
}
