package com.example.edukate.dtos.instructordto;
import java.util.List;
import com.example.edukate.dtos.coursedto.CourseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorCreateDto {
    private String name;
    private String image;
    private Long departmentId;
}
