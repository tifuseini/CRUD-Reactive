package com.example.reactivecrud.config;

import com.example.reactivecrud.model.Course;
import com.example.reactivecrud.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class CourseConfig {

    @Bean
    public CommandLineRunner init(CourseRepository courseRepository) {

        return args -> {

            var course1 = Course.builder()
                    .name("Spring Boot")
                    .category("Framework")
                    .rating("5")
                    .description("Spring Boot is a framework for building applications based on the Spring Framework.")
                    .build();

            var course2 = Course.builder()
                    .name("Spring Data")
                    .category("Framework")
                    .rating("5")
                    .description("Spring Data is a collection of projects that make it easier to store, query, and access data.")
                    .build();

            var course3 = Course.builder()
                    .name("Spring Security")
                    .category("Framework")
                    .rating("5")
                    .description("Spring Security is a framework that focuses on providing both authentication and authorization to Java applications.")
                    .build();

            var course4 = Course.builder()
                    .name("Spring Cloud")
                    .category("Framework")
                    .rating("5")
                    .description("Spring Cloud is a framework that provides tools for developers to quickly build some of the common patterns in distributed systems.")
                    .build();

            Flux.just(course1, course2, course3, course4)
                    .flatMap(courseRepository::save)
                    .subscribe(course -> log.info("Course inserted: {}", course));

        };

    }
}
