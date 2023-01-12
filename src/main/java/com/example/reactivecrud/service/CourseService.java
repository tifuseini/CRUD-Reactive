package com.example.reactivecrud.service;

import com.example.reactivecrud.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {

    Flux<Course> getAllCourses();

    Mono<Course> getCourseById(String id);

    Flux<Course> getCoursesByCategory(String category);

    Mono<Course> saveCourse(Course course);

    Mono<Course> updateCourse(String id, Course course);

    Mono<Void> deleteCourse(String id);

    Mono<Void> deleteAllCourses();
}
