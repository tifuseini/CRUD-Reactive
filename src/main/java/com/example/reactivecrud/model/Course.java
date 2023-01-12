package com.example.reactivecrud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    private String id;

    private String name;

    private String category;

    private String rating;

    private String description;
}
