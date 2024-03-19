package com.example.interviewskeleton.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FirstProblemService {
    private static final String FORBIDDEN_CHARACTER = "a";

  /**
   * I used a CompletableFuture object to run multiple threads
   * asynchronously ensuring a smaller execution time, under 500ms
   * @param words => The list of words to save
   */
  public void saveWords(List<String> words) {
      words.forEach(word -> {
        if (word.contains(FORBIDDEN_CHARACTER)) {
          throw new UnsupportedOperationException("This word is not valid!");
        }
      });

      List<CompletableFuture<Void>> futures = words.stream()
        .map(word -> CompletableFuture.runAsync(() -> {
          saveWordToExternalApi(word);
        }))
        .toList();

      CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenRun(() -> System.out.println("All words have been processed."));
    }

    private void saveWordToExternalApi(String word) {
        try {
            // Here we call external API. We use a sleep of 1s to simulate that it takes a lot of time, and we have no control over it.
            Thread.sleep(1000);
        } catch (Throwable anyException) {
            log.info("[BEST EFFORT] Saving word '{}' failed: {}", word, anyException.getMessage());
        }
    }
}
