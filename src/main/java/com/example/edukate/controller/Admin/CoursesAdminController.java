package com.example.edukate.controller.Admin;

import com.example.edukate.models.Course;
import com.example.edukate.models.Department;
import com.example.edukate.models.Instructor;
import com.example.edukate.repositories.CourseRepository;
import com.example.edukate.repositories.DepartmentRepository;
import com.example.edukate.repositories.InstructorRepository;
import com.example.edukate.service.DepartmentService;
import com.example.edukate.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CoursesAdminController {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;
    private DepartmentService departmentService;
    private InstructorService instructorService;

    @Autowired
    public CoursesAdminController(CourseRepository courseRepository, DepartmentRepository departmentRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("courses", courseRepository.findAll().stream().sorted(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.getId()>o2.getId()){
                    return 1;
                }
                else if (o2.getId() > o1.getId()) return -1;
                else return 0;
            }
        }));
        return "dashboard/course/courses";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Department> departments = departmentRepository.findAll();

        List<Instructor> instructors = instructorRepository.findAll();
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departments);
        model.addAttribute("instructors", instructors);

        return "dashboard/course/course-create"; // Adjust your template path accordingly
    }


    @PostMapping("/create")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
        List<Department> departments = departmentRepository.findAll();
        List<Instructor> instructors = instructorRepository.findAll();

        model.addAttribute("course", course);
        model.addAttribute("departments", departments);
        model.addAttribute("instructors", instructors);
        return "dashboard/course/update";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute("course") Course course) {
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }
//    @PostMapping("/update")
//    public String updateCourse(@ModelAttribute("course") Course updatedCourse) {
//        Course existingCourse = courseRepository.findById(updatedCourse.getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + updatedCourse.getId()));
//
//        // Update only editable fields
//        existingCourse.setName(updatedCourse.getName());
//        existingCourse.setDescription(updatedCourse.getDescription());
//        existingCourse.setImage(updatedCourse.getImage());
//
//        // You can add more fields here as needed
//
//        // Save the updated course
//        courseRepository.save(existingCourse);
//
//        return "redirect:/admin/courses";
//    }

    @GetMapping("/remove/{id}")
    public String courseRemove(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
        course.setDeleted(true);
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/activate/{id}")
    public String activateCourse(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
        course.setDeleted(false);
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }
}