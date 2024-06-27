package com.example.edukate.config;

import com.example.edukate.dtos.coursedto.CourseCreateDto;
import com.example.edukate.dtos.departmentdto.DepartmentDto;
import com.example.edukate.models.Course;
import com.example.edukate.models.Department;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.createTypeMap(Department.class, DepartmentDto.class);
        return mapper;
    }
}
