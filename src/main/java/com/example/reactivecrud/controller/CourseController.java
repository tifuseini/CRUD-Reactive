package com.example.reactivecrud.controller;

import com.example.reactivecrud.model.Course;
import com.example.reactivecrud.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/courses/")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public Flux<Course> getAllCourses() {
        return courseService.getAllCourses()
                .doOnError(throwable -> log.error("Error occurred while getting all courses", throwable));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Course>> getCourseById(String id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("category/{name}")
    public Flux<Course> getCoursesByCategory(String name) {
        return courseService.getCoursesByCategory(name)
                .doOnError(throwable ->
                        log.error("Error occurred while getting courses by category: {}", name, throwable));
    }

    @PostMapping
    public Mono<Course> saveCourse(Course course) {
        return courseService.saveCourse(course)
                .doOnSuccess(savedCourse -> log.info("Course saved successfully: {}", savedCourse))
                .doOnError(throwable -> log.error("Error occurred while saving course: {}", course, throwable));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Course>> updateCourse(String id, Course course) {
        return courseService.updateCourse(id, course)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(updatedCourse -> log.info("Course updated successfully: {}", updatedCourse))
                .doOnError(throwable -> log.error("Error occurred while updating course: {}", course, throwable));
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCourse(String id) {
        return courseService.deleteCourse(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnSuccess(deletedCourse -> log.info("Course deleted successfully: {}", deletedCourse))
                .doOnError(throwable -> log.error("Error occurred while deleting course: {}", id, throwable));
    }

    @DeleteMapping
    public Mono<Void> deleteAllCourses() {
        return courseService.deleteAllCourses()
                .doOnSuccess(deletedCourses -> log.info("All courses deleted successfully: {}", deletedCourses))
                .doOnError(throwable -> log.error("Error occurred while deleting all courses", throwable));
    }
}
