package com.example.edukate.controller.Admin;

import com.example.edukate.models.Department;
import com.example.edukate.models.Student;
import com.example.edukate.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentAdminController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentAdminController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentRepository.findAll().stream().sorted(new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                if (o1.getId() > o2.getId()) return 1;
                else if (o2.getId() > o1.getId()) return -1;
                else return 0;
            }
        }));

        return "dashboard/department/departments";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "dashboard/department/department-create";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute("department") Department department) {
        departmentRepository.save(department);
        return "redirect:/admin/departments";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department id: " + id));
        model.addAttribute("department", department);
        return "dashboard/department/update";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute("department") Department department) {
        departmentRepository.save(department);
        return "redirect:/admin/departments";
    }

    @GetMapping("/remove/{id}")
    public String departmentRemove(@PathVariable Long id) {
        var department = departmentRepository.findById(id);
        department.get().setDeleted(true);
        departmentRepository.save(department.get());
        return "redirect:/admin/departments";
    }

    @GetMapping("/activate/{id}")
    public String activateDepartment(@PathVariable Long id) {
        var department = departmentRepository.findById(id);
        department.get().setDeleted(false);
        departmentRepository.save(department.get());
        return "redirect:/admin/departments";
    }
}
