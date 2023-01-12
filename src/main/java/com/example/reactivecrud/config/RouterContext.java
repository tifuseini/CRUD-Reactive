package com.example.reactivecrud.config;

import com.example.reactivecrud.component.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterContext {

    @Bean
    RouterFunction<ServerResponse> routes(CourseHandler courseHandler) {
        return route(GET("/courses")
                .and(accept(MediaType.APPLICATION_JSON)),
                courseHandler::findAllCourses);
    }


}
