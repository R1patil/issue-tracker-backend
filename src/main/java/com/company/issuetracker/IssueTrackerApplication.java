package com.company.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IssueTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("1234"));

    }
}
