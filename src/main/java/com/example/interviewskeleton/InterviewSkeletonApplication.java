package com.example.interviewskeleton;

import com.example.interviewskeleton.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class InterviewSkeletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewSkeletonApplication.class, args);
    }

}
