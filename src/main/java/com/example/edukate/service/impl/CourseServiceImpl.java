package com.example.edukate.service.impl;

import com.example.edukate.dtos.coursedto.CourseCreateDto;
import com.example.edukate.dtos.coursedto.CourseDetailDto;
import com.example.edukate.dtos.coursedto.CourseDto;
import com.example.edukate.dtos.coursedto.CourseUpdateDto;
import com.example.edukate.models.Course;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.InstructorRepository;
import com.example.edukate.service.CourseService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final InstructorRepository instructorRepository;
    private final ModelMapper mapper;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(InstructorRepository instructorRepository, ModelMapper mapper, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.mapper = mapper;
        this.courseRepository = courseRepository;
    }



    @Override
    public List<CourseDto> getCourses() {
        return courseRepository.findAll().stream()
                .filter(course -> !course.isDeleted())
                .map(course -> mapper.map(course,CourseDto.class))
                .toList();
    }

    @Override
    public CourseDto courseDto(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(RuntimeException::new);
        CourseDto courseDto=mapper.map(course,CourseDto.class);
        return courseDto;
    }
//????
    @Override
    @Transactional
    public void addCourse(CourseCreateDto courseCreateDto) {
        var instructorId=courseCreateDto.getInstructorId();
        var instructor=instructorRepository.findById(instructorId).orElseThrow(RuntimeException::new);
        courseRepository.save(mapper.map(courseCreateDto,Course.class));
//?????
    }

    @Override
    @Transactional
    public void updateCourse(Long id, CourseUpdateDto courseUpdateDto) {
        Course course=courseRepository.findById(id).orElseThrow(RuntimeException::new);
        var instructorId=courseUpdateDto.getInstructorId();
        var instructor=instructorRepository.findById(instructorId).orElseThrow(RuntimeException::new);

        courseUpdateDto.setId(id);
        courseRepository.save(mapper.map(courseUpdateDto,Course.class));

    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(RuntimeException::new);
        course.setDeleted(true);
        courseRepository.save(course);
    }
}
