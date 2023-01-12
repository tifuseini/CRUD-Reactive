package com.example.reactivecrud.serviceImpl;

import com.example.reactivecrud.model.Course;
import com.example.reactivecrud.repository.CourseRepository;
import com.example.reactivecrud.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Flux<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Mono<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public Flux<Course> getCoursesByCategory(String category) {
        return courseRepository.findAllByCategory(category);
    }

    @Override
    public Mono<Course> saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Mono<Course> updateCourse(String id, Course course) {
        return courseRepository.findById(id)
                .flatMap(existingCourse -> {
                    existingCourse.setName(course.getName());
                    existingCourse.setCategory(course.getCategory());
                    existingCourse.setRating(course.getRating());
                    existingCourse.setDescription(course.getDescription());
                    return courseRepository.save(existingCourse);
                });
    }

    @Override
    public Mono<Void> deleteCourse(String id) {
        return courseRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllCourses() {
        return courseRepository.deleteAll();
    }
}
