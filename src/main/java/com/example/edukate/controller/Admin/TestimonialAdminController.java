package com.example.edukate.controller.Admin;

import com.example.edukate.dtos.testimonialdto.TestimonialCreateDto;
import com.example.edukate.models.Student;
import com.example.edukate.models.Testimonial;
import com.example.edukate.repositories.StudentRepository;
import com.example.edukate.repositories.TestimonialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/testimonials")
public class TestimonialAdminController {
    private final TestimonialRepository testimonialRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    @Autowired
    public TestimonialAdminController(TestimonialRepository testimonialRepository, StudentRepository studentRepository, ModelMapper mapper) {
        this.testimonialRepository = testimonialRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("testimonials", testimonialRepository.findAll().stream().sorted(new Comparator<Testimonial>() {
            @Override
            public int compare(Testimonial o1, Testimonial o2) {
                if (o1.getId() > o2.getId()) return 1;
                else if (o2.getId() > o1.getId()) return -1;
                else return 0;
            }
        }));
        return "dashboard/testimonial/testimonials";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("testimonial", new Testimonial());
        return "dashboard/testimonial/testimonial-create";
    }

    @PostMapping("/create")
    public String createTestimonial(Model model, @ModelAttribute("testimonial") TestimonialCreateDto testimonialDto) {
        // Fetch the student based on the ID
        Student student = studentRepository.findById(testimonialDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + testimonialDto.getStudentId()));

        // Create and populate the testimonial entity
        Testimonial testimonial = mapper.map(testimonialDto, Testimonial.class);
        testimonial.setStudent(student);

        // Save the testimonial entity
        testimonialRepository.save(testimonial);

        return "redirect:/admin/testimonials";
    }


    @GetMapping("/remove/{id}")
    public String testimonialRemove(@PathVariable Long id) {
        var testimonial = testimonialRepository.findById(id);
        testimonial.get().setDeleted(true);

        testimonialRepository.save(testimonial.get());
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/activate/{id}")
    public String activity(@PathVariable Long id) {
        var testimonial = testimonialRepository.findById(id);
        testimonial.get().setDeleted(false);

        testimonialRepository.save(testimonial.get());
        return "redirect:/admin/testimonials";
    }
}
