//package com.example.edukate.dtos.coursedto;
//
//public class CourseCreateDto {
//    private String name;
//    private Long instructorId;
//
//    private int rating;
//
//
//
//}
package com.example.edukate.dtos.coursedto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateDto {
    private String name;
    private String description;
    private String image;
    private String durationTime;
    private int lectureCount;
    private String skillLevel;
    private String language;
    private double price;

    //Service validation
    private Long instructorId;
}