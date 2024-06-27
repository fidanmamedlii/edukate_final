package com.example.edukate.controller;

import com.example.edukate.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
    private CourseService service;

    public CoursesController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/courses")
    public String index(Model model){
        var courses = service.getCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    @GetMapping("/courses/detail")
    public String detail(){
        return "courseDetails";
    }
}

