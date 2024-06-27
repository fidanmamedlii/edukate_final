package com.example.edukate.service.impl;

import com.example.edukate.dtos.coursedto.CourseDetailDto;
import com.example.edukate.models.Course;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.service.CourseDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseDetailServiceImpl implements CourseDetailService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Autowired
    public CourseDetailServiceImpl(CourseRepository courseRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CourseDetailDto> getCourseDetails() {
        return courseRepository.findAll().stream()
                .filter(course -> !course.isDeleted())
                .map(course -> mapper.map(course,CourseDetailDto.class))
                .toList();
    }

    @Override
    public CourseDetailDto courseDetailDto(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(RuntimeException::new);
        CourseDetailDto courseDetailDto=mapper.map(course,CourseDetailDto.class);
        return courseDetailDto;
    }
}
