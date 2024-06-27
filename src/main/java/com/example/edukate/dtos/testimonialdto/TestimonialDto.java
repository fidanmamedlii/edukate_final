package com.example.edukate.dtos.testimonialdto;

import com.example.edukate.dtos.studentdto.StudentDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TestimonialDto {
    private Long id;
    private String content;
    private StudentDto student;
    private String image;
}
