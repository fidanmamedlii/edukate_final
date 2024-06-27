package com.example.edukate.dtos.coursedto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourseDto {
    private Long id;
    private String name;
    private double rating;
    private String image;
    private String instructorName;
}