package com.example.reactivecrud.component;

import com.example.reactivecrud.model.Course;
import com.example.reactivecrud.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseHandler {

    private final CourseService courseService;

    public Mono<ServerResponse> findAllCourses(ServerRequest request) {
        var courses = courseService.getAllCourses();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courses, Course.class);
    }

    public Mono<ServerResponse> findCourseById(ServerRequest request) {
        var id = request.pathVariable("id");
        var course = courseService.getCourseById(id);
        return course.flatMap(c -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(c))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createCourse(ServerRequest request) {
        var course = request.bodyToMono(Course.class);

        return course.flatMap(c -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courseService.saveCourse(c), Course.class));
    }
}
