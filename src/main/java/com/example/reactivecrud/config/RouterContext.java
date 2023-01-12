package com.example.reactivecrud.config;

import com.example.reactivecrud.component.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterContext {

    @Bean
    RouterFunction<ServerResponse> routes(CourseHandler courseHandler) {
        return route(GET("/courses")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::findAllCourses)
                .andRoute(GET("/courses/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::findCourseById)
                .andRoute(POST("/courses")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::createCourse)
                .andRoute(PUT("/courses/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::updateCourse)
                .andRoute(DELETE("/courses/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::deleteCourse)
                .andRoute(DELETE("/courses")
                        .and(accept(MediaType.APPLICATION_JSON)),
                        courseHandler::deleteAllCourses);

    }


}
