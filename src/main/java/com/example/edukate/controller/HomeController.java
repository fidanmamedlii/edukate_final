package com.example.edukate.controller;


import com.example.edukate.models.ContactMessage;
import com.example.edukate.service.InstructorService;
import com.example.edukate.service.TestimonialService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import com.example.edukate.service.CourseService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private CourseService service;
    private InstructorService instructorService;
    private TestimonialService testimonialService;
    @Autowired
    private JavaMailSender mailSender;

    public HomeController(InstructorService instructorService, CourseService service, TestimonialService testimonialService) {
        this.service = service;
        this.instructorService = instructorService;
        this.testimonialService=testimonialService;
    }
    private void sendEmail(ContactMessage contactMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fidanmammadli28@gmail.com"); // Replace with your email address
        message.setTo(contactMessage.getEmail()); // Replace with your email address
        message.setSubject(contactMessage.getSubject());
        message.setText("Name: " + contactMessage.getName() + "\nEmail: " + contactMessage.getEmail() + "\nMessage: " + contactMessage.getMessage());

        mailSender.send(message);
    }

    @GetMapping("/")
    public String index(Model model){
        var courses = service.getCourses();
        var instructors=instructorService.getInstructors();
        var testimonials=testimonialService.getTestimonials();
        model.addAttribute("instructors",instructors);
        model.addAttribute("testimonials",testimonials);
        model.addAttribute("courses",courses);
        model.addAttribute("contactForm", new ContactMessage());
        return "home";
    }

}
