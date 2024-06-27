package com.example.edukate.service;

import com.example.edukate.dtos.studentdto.StudentCreateDto;
import com.example.edukate.dtos.studentdto.StudentDto;
import com.example.edukate.dtos.studentdto.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();
    StudentDto getStudent(Long id);
    void addStudent(StudentCreateDto studentCreateDto);
    void updateStudent(Long id, StudentUpdateDto studentUpdateDto);
    void deleteStudent(Long id);
}
