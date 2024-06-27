package com.example.edukate.dtos.instructordto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorUpdateDto {
    private Long id;
    private String name;
    private Long departmentId;
}
