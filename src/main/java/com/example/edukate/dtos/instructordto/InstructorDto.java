package com.example.edukate.dtos.instructordto;

import com.example.edukate.dtos.coursedto.CourseDto;
import com.example.edukate.dtos.departmentdto.DepartmentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorDto {
    private Long id;
    private String image;
    private String name;
    private List<CourseDto> courses;
    private DepartmentDto department;
}
