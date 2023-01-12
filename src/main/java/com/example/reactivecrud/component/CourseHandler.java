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
}
