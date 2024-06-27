package com.example.edukate.dtos.departmentdto;

import com.example.edukate.dtos.coursedto.CourseDto;
import com.example.edukate.dtos.instructordto.InstructorDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private Long id;
    private String name;
    private List<InstructorDto> instructors;
    private List<CourseDto> courses;
}
