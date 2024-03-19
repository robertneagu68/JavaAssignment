package com.example.interviewskeleton.service;

import com.example.interviewskeleton.config.GreetingProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the GreetingServiceImpl.
 * Tests the functionality of generating greeting messages based on the part of the day and locale.
 */
@ExtendWith(MockitoExtension.class)
public class GreetingServiceImplTest {
    @Mock
    private GreetingProperties greetingProperties;

    private GreetingServiceImpl greetingService;

  /**
   * Sets up the testing environment before each test.
   * This includes initializing the GreetingServiceImpl with mocked GreetingProperties.
   */
    @BeforeEach
    void setUp() {
      greetingService = Mockito.spy(new GreetingServiceImpl(greetingProperties));
    }

  /**
   * Tests the getGreeting method for the morning in English.
   * Ensures that the correct morning greeting is returned for the "en" locale.
   */
  @Test
    void testGetGreetingMorningEnglish() {
      Map<String, String> morningGreetings = Map.of("en", "Good morning, {name}!", "es", "¡Buenos días, {name}!");
      Map<String, String> afternoonGreetings = Map.of("en", "Good afternoon, {name}!", "es", "¡Buenas tardes, {name}!");
      Map<String, String> eveningGreetings = Map.of("en", "Good evening, {name}!", "es", "¡Buenas noches, {name}!");

      LocalTime now = LocalTime.now();
      //LocalTime now = LocalTime.of(9,35);
      //LocalTime now = LocalTime.of(15,30);
      //LocalTime now = LocalTime.of(20,55);
      if (now.isBefore(LocalTime.of(12, 0))) {
        when(greetingProperties.getMorning()).thenReturn(morningGreetings);
        String result = greetingService.getGreeting("John", "en");
        assertEquals("Good morning, John!", result);
      } else if (now.isBefore(LocalTime.of(18, 0))) {
        when(greetingProperties.getAfternoon()).thenReturn(afternoonGreetings);
        String result = greetingService.getGreeting("John", "en");
        assertEquals("Good afternoon, John!", result);
      } else {
        when(greetingProperties.getEvening()).thenReturn(eveningGreetings);
        String result = greetingService.getGreeting("John", "en");
        assertEquals("Good evening, John!", result);
      }
    }

  /**
   * Tests the getGreeting method for the morning in Spanish.
   * Ensures that the correct morning greeting is returned for the "es" locale.
   */
  @Test
  void testGetGreetingMorningSpanish() {
    Map<String, String> morningGreetings = Map.of("es", "¡Buenos días, {name}!");
    Map<String, String> afternoonGreetings = Map.of("es", "¡Buenas tardes, {name}!");
    Map<String, String> eveningGreetings = Map.of("es", "¡Buenas noches, {name}!");

    LocalTime now = LocalTime.now();
    //LocalTime now = LocalTime.of(9,35);
    //LocalTime now = LocalTime.of(15,30);
    //LocalTime now = LocalTime.of(20,55);

    if (now.isBefore(LocalTime.of(12, 0))) {
      when(greetingProperties.getMorning()).thenReturn(morningGreetings);
      String result = greetingService.getGreeting("John", "es");
      assertEquals("¡Buenos días, John!", result);
    } else if (now.isBefore(LocalTime.of(18, 0))) {
      when(greetingProperties.getAfternoon()).thenReturn(afternoonGreetings);
      String result = greetingService.getGreeting("John", "es");
      assertEquals("¡Buenas tardes, John!", result);
    } else {
      when(greetingProperties.getEvening()).thenReturn(eveningGreetings);
      String result = greetingService.getGreeting("John", "es");
      assertEquals("¡Buenas noches, John!", result);
    }
  }

}
