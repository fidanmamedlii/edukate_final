package com.example.edukate.dtos.coursedto;

import com.example.edukate.dtos.instructordto.InstructorDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String applyDescription;
    private String certificationDescription;
    private Date startDate;
    private String durationTime;
    private String classDuration;
    private String skillLevel;
    private String language;
    private int studentCapacity;
    private String assessments;
    private double price;
    private InstructorDto instructor;
}