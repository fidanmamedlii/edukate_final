package com.example.edukate.service.impl;

import com.example.edukate.dtos.departmentdto.DepartmentCreateDto;
import com.example.edukate.dtos.departmentdto.DepartmentDto;
import com.example.edukate.dtos.departmentdto.DepartmentUpdateDto;
import com.example.edukate.models.Department;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.DepartmentRepository;
import com.example.edukate.repositories.InstructorRepository;
import com.example.edukate.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll().stream()
                .filter(department -> !department.isDeleted())
                .map(department -> mapper.map(department,DepartmentDto.class))
                .toList();
    }

    @Override
    public DepartmentDto departmentDetail(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(RuntimeException::new);
        DepartmentDto departmentDto=mapper.map(department,DepartmentDto.class);
        return departmentDto;
    }

    @Override
    @Transactional
    public void addDepartment(DepartmentCreateDto departmentCreateDto) {
        departmentRepository.save(mapper.map(departmentCreateDto,Department.class));
    }

    @Override
    @Transactional
    public void updateDepartment(Long id, DepartmentUpdateDto departmentUpdateDto) {
        Department department=departmentRepository.findById(id).orElseThrow(RuntimeException::new);
        departmentUpdateDto.setId(id);
        departmentRepository.save(mapper.map(departmentUpdateDto,Department.class));
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(RuntimeException::new);
        department.setDeleted(true);
        departmentRepository.save(department);
    }
}
