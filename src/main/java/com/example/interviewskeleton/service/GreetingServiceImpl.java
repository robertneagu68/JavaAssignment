package com.example.interviewskeleton.service;

import com.example.interviewskeleton.config.GreetingProperties;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Map;

/**
 * Implementation of the GreetingService.
 * Provides functionality to generate greeting messages based on the part of the day and locale.
 */
@Service
public class GreetingServiceImpl implements GreetingService {

  private final GreetingProperties greetingProperties;

  /**
   * Constructs a new GreetingServiceImpl with the necessary configuration properties.
   *
   * @param greetingProperties The configuration properties for greeting messages.
   */
  public GreetingServiceImpl(GreetingProperties greetingProperties) {
    this.greetingProperties = greetingProperties;
  }

  /**
   * Returns a localized greeting message based on the time of day and provided locale.
   *
   * @param name The name of the person to greet.
   * @param locale The locale specifying the language of the greeting.
   * @return A greeting message tailored to the time of day and specified locale.
   */
  @Override
  public String getGreeting(String name, String locale) {
    String partOfDay = getPartOfDay();
    Map<String, String> greetings = switch(partOfDay) {
      case "morning" -> greetingProperties.getMorning();
      case "afternoon" -> greetingProperties.getAfternoon();
      case "evening" -> greetingProperties.getEvening();
      default -> throw new IllegalStateException("Unexpected value: " + partOfDay);
    };

    String greetingTemplate = greetings.getOrDefault(locale, "Hello, {name}!");
    return greetingTemplate.replace("{name}", name);
  }

  /**
   * Determines the part of the day based on the current time.
   *
   * @return A string representing the part of the day ("morning", "afternoon", or "evening").
   */
  private String getPartOfDay() {
    LocalTime now = LocalTime.now();
    // For testing purposes
    //LocalTime now = LocalTime.of(9,35);
    //LocalTime now = LocalTime.of(15,30);
    //LocalTime now = LocalTime.of(20,55);
    if (now.isBefore(LocalTime.of(12, 0))) {
      return "morning";
    } else if (now.isBefore(LocalTime.of(18, 0))) {
      return "afternoon";
    } else {
      return "evening";
    }
  }
}
