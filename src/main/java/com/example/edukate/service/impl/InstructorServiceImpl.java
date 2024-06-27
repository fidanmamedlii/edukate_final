package com.example.edukate.service.impl;

import com.example.edukate.dtos.instructordto.InstructorCreateDto;
import com.example.edukate.dtos.instructordto.InstructorDto;
import com.example.edukate.dtos.instructordto.InstructorUpdateDto;
import com.example.edukate.models.Department;
import com.example.edukate.models.Instructor;
import com.example.edukate.models.Testimonial;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.DepartmentRepository;
import com.example.edukate.repositories.InstructorRepository;
import com.example.edukate.service.InstructorService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private final InstructorRepository instructorRepository;
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final ModelMapper mapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.instructorRepository = instructorRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<InstructorDto> getInstructors() {
        return instructorRepository.findAll().stream()
                .filter(instructor -> !instructor.isDeleted())
                .map(instructor -> mapper.map(instructor, InstructorDto.class))
                .toList();
    }

    @Override
    public InstructorDto instructorDetail(Long id) {
        Instructor instructor=instructorRepository.findById(id).orElseThrow(RuntimeException::new);
        InstructorDto instructorDto = mapper.map(instructor,InstructorDto.class);
        return instructorDto;
    }

    @Override
    @Transactional
    public void addInstructor(InstructorCreateDto instructorCreateDto) {
        var departmentId=instructorCreateDto.getDepartmentId();
        var department=departmentRepository.findById(departmentId).orElseThrow(RuntimeException::new);
        instructorRepository.save(mapper.map(instructorCreateDto,Instructor.class));
    }

    @Override
    @Transactional
    public void updateInstructor(Long id, InstructorUpdateDto instructorUpdateDto) {
        Instructor instructor=instructorRepository.findById(id).orElseThrow(RuntimeException::new);
        var departmentId=instructorUpdateDto.getDepartmentId();
        var department=departmentRepository.findById(departmentId).orElseThrow(RuntimeException::new);

        instructorUpdateDto.setId(id);
        instructorRepository.save(mapper.map(instructorUpdateDto,Instructor.class));

    }

    @Override
    @Transactional
    public void deleteInstructor(Long id) {
        Instructor instructor=instructorRepository.findById(id).orElseThrow(RuntimeException::new);
        instructor.setDeleted(true);
        instructorRepository.save(instructor);

    }
}
