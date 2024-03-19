
package com.example.interviewskeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String seoName;
    private String productCode;


/**
   * After debugging, I found a few issues on these 2 methods
   * 1. In the generateSeoName method, the sequence for constructing the seoName was missing whitespaces
   * at the "-" character and there was an extra whitespace before the "(" when displaying the year
   * 2. In the generateProductCode() method, in the first if the check for whitespace was missing
   * and also there was the check for digit characters that was missing, resulting in the numbers
   * not being used to further create the productCode
   * I could also just leave a blank else and append the character, due to the structure of the productCode in the unit test
   */

  public void generateSeoName() {
        this.seoName = author + " - " + title + " (" + year + ")";
    }

    public void generateProductCode() {
        if (seoName == null) {
            productCode = null;
            return;
        }

        StringBuilder codeBuilder = new StringBuilder();
        for (char ch : seoName.toCharArray()) {
            if (ch == '(' || ch == ')' || ch == '-' || ch == ' ') {
                codeBuilder.append('.');
            } else if ("AEIOUaeiou".contains(String.valueOf(ch))) {
                codeBuilder.append('1');
            } else if (Character.isAlphabetic(ch)) {
                codeBuilder.append('0');
            } else if (Character.isDigit(ch)){
              codeBuilder.append(ch);
            }
        }
        this.productCode = codeBuilder.toString();
    }
}
