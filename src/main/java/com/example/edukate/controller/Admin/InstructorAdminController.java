package com.example.edukate.controller.Admin;

import com.example.edukate.dtos.instructordto.InstructorCreateDto;
import com.example.edukate.models.Department;
import com.example.edukate.models.Instructor;
import com.example.edukate.repositories.DepartmentRepository;
import com.example.edukate.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/instructors")
public class InstructorAdminController {
    private final InstructorRepository instructorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public InstructorAdminController(InstructorRepository instructorRepository, DepartmentRepository departmentRepository) {
        this.instructorRepository = instructorRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("instructors", instructorRepository.findAll().stream().sorted(new Comparator<Instructor>() {
            @Override
            public int compare(Instructor o1, Instructor o2) {
                if (o1.getId() > o2.getId()) return 1;
                else if (o2.getId() > o1.getId()) return -1;
                else return 0;
            }
        }));
        return "dashboard/instructor/instructors";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("instructor", new Instructor());
        return "dashboard/instructor/instructor-create";
    }

//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("instructor", new Instructor());
//        return "dashboard/instructor/instructor-create";
//    }

    @PostMapping("/create")
    public String createInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorRepository.save(instructor);
        return "redirect:/admin/instructors";
    }
//    @PostMapping("/create")
//    public String createInstructor(Model model, @ModelAttribute("instructor") InstructorCreateDto instructorDto) {
//        Instructor instructor=mapper.map(instructorDto, Instructor.class);
//        instructorRepository.save(instructor);
//        return "redirect:/admin/instructors";
//    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        List<Department> departments=departmentRepository.findAll();
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid instructor id: " + id));
        model.addAttribute("instructor", instructor);
        model.addAttribute("departments",departments);
        return "dashboard/instructor/update";
    }

    @PostMapping("/update")
    public String updateInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorRepository.save(instructor);
        return "redirect:/admin/instructors";
    }

    @GetMapping ("/remove/{id}")
    public String instructorRemove(@PathVariable Long id) {
        var instructor = instructorRepository.findById(id);
        instructor.get().setDeleted(true);

        instructorRepository.save(instructor.get());
        return "redirect:/admin/instructors";
    }
    @GetMapping("/activate/{id}")
    public String activity(@PathVariable Long id)
    {
        var instructor=instructorRepository.findById(id);
        instructor.get().setDeleted(false);

        instructorRepository.save(instructor.get());
        return "redirect:/admin/instructors";
    }
}
