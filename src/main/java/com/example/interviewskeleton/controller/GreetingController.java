package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for greeting operations.
 * Provides an endpoint to get a greeting message based on the time of day, user's name, and locale.
 */
@RestController
@RequiredArgsConstructor
public class GreetingController {

  private final GreetingService greetingService;

  /**
   * Returns a greeting message for the given name and locale.
   *
   * @param name The name of the person to greet.
   * @param locale The locale to use for the greeting message. Defaults to "en" if not provided.
   * @return A localized greeting message tailored to the time of day.
   */
  @GetMapping("/greet/{name}")
  public String greet(@PathVariable String name, @RequestParam(defaultValue = "en") String locale) {
    return greetingService.getGreeting(name, locale);
  }
}
