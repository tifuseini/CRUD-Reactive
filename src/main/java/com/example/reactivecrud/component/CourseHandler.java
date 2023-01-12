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

    public Mono<ServerResponse> updateCourse(ServerRequest request) {
        var id = request.pathVariable("id");
        var course = request.bodyToMono(Course.class);
        var existingCourse = courseService.getCourseById(id);

       return course.zipWith(
                existingCourse,
                (c, existing) -> Course.builder()
                        .id(existing.getId())
                        .name(c.getName())
                        .category(c.getCategory())
                        .rating(c.getRating())
                        .description(c.getDescription())
                        .build())
                .flatMap(c -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(courseService.updateCourse(id, c), Course.class))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> deleteCourse(ServerRequest request) {
        var id = request.pathVariable("id");
        var course = courseService.getCourseById(id);
        return course.flatMap(c -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courseService.deleteCourse(id), Void.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteAllCourses(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courseService.deleteAllCourses(), Void.class);
    }

    public Mono<ServerResponse> findCoursesByCategory(ServerRequest request) {
        var name = request.pathVariable("name");
        var courses = courseService.getCoursesByCategory(name);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(courses, Course.class);
    }
}
