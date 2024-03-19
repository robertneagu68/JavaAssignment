package com.example.interviewskeleton.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

public class TokenAuth extends AbstractAuthenticationToken {
  private final String token;

  public TokenAuth(String token) {
    super(null);
    this.token = token;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }
}
