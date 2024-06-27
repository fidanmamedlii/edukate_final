package com.example.edukate.service;

import com.example.edukate.dtos.departmentdto.DepartmentCreateDto;
import com.example.edukate.dtos.departmentdto.DepartmentDto;
import com.example.edukate.dtos.departmentdto.DepartmentUpdateDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getDepartments();
    DepartmentDto departmentDetail(Long id);
    void addDepartment(DepartmentCreateDto departmentCreateDto);
    void updateDepartment(Long id, DepartmentUpdateDto departmentUpdateDto);
    void deleteDepartment(Long id);
}
