package com.example.edukate.controller.Admin;

import com.example.edukate.models.Course;
import com.example.edukate.models.Student;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/students")
public class StudentAdminController {
    private final StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public StudentAdminController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", studentRepository.findAll().stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getId() > o2.getId()) return 1;
                else if (o2.getId() > o1.getId()) return -1;
                else return 0;
            }
        }));
        return "dashboard/student/students";
    }
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("student", new Student());
//        return "dashboard/student/student-create";
//    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Course> courses = courseRepository.findAll(); // Fetch courses from database
        model.addAttribute("courses", courses);
        return "dashboard/student/student-create";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/admin/students";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        List<Course> courses = courseRepository.findAll();
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "dashboard/student/update";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/admin/students";
    }
    @GetMapping ("/remove/{id}")
    public String studentRemove(@PathVariable Long id) {
        var student = studentRepository.findById(id);
        student.get().setDeleted(true);

        studentRepository.save(student.get());
        return "redirect:/admin/students";
    }
    @GetMapping("/activate/{id}")
    public String activity(@PathVariable Long id)
    {
        var student=studentRepository.findById(id);
        student.get().setDeleted(false);

        studentRepository.save(student.get());
        return "redirect:/admin/students";
    }
}
