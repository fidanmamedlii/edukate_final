package com.example.edukate.dtos.studentdto;

import com.example.edukate.dtos.coursedto.CourseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentUpdateDto {
    private Long id;
    private String name;
    private List<Long> courseIds;
}