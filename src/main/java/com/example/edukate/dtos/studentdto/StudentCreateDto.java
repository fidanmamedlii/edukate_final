package com.example.edukate.dtos.studentdto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateDto {
    private String name;
    private String image;
    private String surname;
    private List<Long> courseIds;
}
