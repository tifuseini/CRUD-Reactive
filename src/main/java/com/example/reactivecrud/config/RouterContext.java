package com.example.reactivecrud.config;

import com.example.reactivecrud.component.CourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterContext {

    @Bean
    RouterFunction<ServerResponse> routes(CourseHandler courseHandler) {
        return routes();
    }
}
