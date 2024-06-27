package com.example.edukate.service.impl;

import com.example.edukate.dtos.studentdto.StudentCreateDto;
import com.example.edukate.dtos.studentdto.StudentDto;
import com.example.edukate.dtos.studentdto.StudentUpdateDto;
import com.example.edukate.models.Student;
import com.example.edukate.models.Testimonial;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.StudentRepository;
import com.example.edukate.service.StudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream()
                .filter(student -> !student.isDeleted())
                .map(student -> mapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        StudentDto studentDto = mapper.map(student,StudentDto.class);
        return studentDto;
    }

    @Override
    @Transactional
    public void addStudent(StudentCreateDto studentCreateDto) {
        for (var courseId : studentCreateDto.getCourseIds()) {
            var course = courseRepository.findById(courseId).orElseThrow(RuntimeException::new);
        }

        studentRepository.save(mapper.map(studentCreateDto, Student.class));
    }

    @Override
    @Transactional
    public void updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student=studentRepository.findById(id).orElseThrow(RuntimeException::new);
        for( var courseId: studentUpdateDto.getCourseIds()){
            var course=courseRepository.findById(courseId).orElseThrow(RuntimeException::new);
        }

        studentUpdateDto.setId(id);
        studentRepository.save(mapper.map(studentUpdateDto,Student.class));

    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(RuntimeException::new);
        student.setDeleted(true);
        studentRepository.save(student);

    }
}
