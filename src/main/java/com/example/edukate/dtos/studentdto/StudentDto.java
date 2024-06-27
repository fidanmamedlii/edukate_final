package com.example.edukate.dtos.studentdto;

import com.example.edukate.dtos.coursedto.CourseDto;
import com.example.edukate.dtos.departmentdto.DepartmentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String image;
    private String name;
    private String surname;
    private List<CourseDto> courses;
}
