package com.example.edukate.dtos.coursedto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseUpdateDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String durationTime;
    private String skillLevel;
    private String language;
    private String assessments;
    private int lectureCount;
    private double price;
    private Long instructorId;
}