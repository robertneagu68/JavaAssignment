package com.example.interviewskeleton.service;

/**
 * Service interface for generating greeting messages.
 */
public interface GreetingService {
  /**
   * Generates a greeting message for the specified name and locale.
   *
   * @param name The name of the person to greet.
   * @param locale The locale specifying the language of the greeting.
   * @return A greeting message.
   */
  String getGreeting(String name, String locale);
}
