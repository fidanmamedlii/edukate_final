package com.example.edukate.service;

import com.example.edukate.dtos.coursedto.CourseDetailDto;


import java.util.List;

public interface CourseDetailService {
    List<CourseDetailDto> getCourseDetails();
    CourseDetailDto courseDetailDto(Long id);
}
