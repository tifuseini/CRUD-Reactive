package com.example.reactivecrud.component;

import com.example.reactivecrud.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseHandler {

    private final CourseService courseService;
}
