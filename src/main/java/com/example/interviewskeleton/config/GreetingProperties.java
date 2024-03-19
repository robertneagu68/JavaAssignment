package com.example.interviewskeleton.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Configuration properties holder for greeting messages.
 * Maps the greeting configuration from the application.yaml file to the objects.
 * The configuration specifies different greeting messages for various parts of the day (morning, afternoon, evening)
 * and supports multiple locales.
 */
@Configuration
@ConfigurationProperties(prefix = "greeting")
public class GreetingProperties {
  private Map<String, String> morning;
  private Map<String, String> afternoon;
  private Map<String, String> evening;

  public Map<String, String> getMorning() {
    return morning;
  }

  public void setMorning(Map<String, String> morning) {
    this.morning = morning;
  }

  public Map<String, String> getAfternoon() {
    return afternoon;
  }

  public void setAfternoon(Map<String, String> afternoon) {
    this.afternoon = afternoon;
  }

  public Map<String, String> getEvening() {
    return evening;
  }

  public void setEvening(Map<String, String> evening) {
    this.evening = evening;
  }
}
