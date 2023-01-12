package com.example.reactivecrud.repository;

import com.example.reactivecrud.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

    Flux<Course> findAllByCategory(String category);

}
