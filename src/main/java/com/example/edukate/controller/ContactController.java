//package com.example.edukate.controller;
//
//
//import com.example.edukate.models.ContactMessage;
//import com.example.edukate.service.ContactMessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ContactController {
//
//    @Autowired
//    private JavaMailSender mailSender;
//    @Autowired
//    private ContactMessageService contactMessageService;
//
//    @GetMapping("/contact")
//    public String showContactForm(Model model) {
//        model.addAttribute("contactMessage", new ContactMessage());
//        return "contact"; // Ensure this matches your Thymeleaf template name
//    }
//
//    @PostMapping("/contact")
//    public String submitContactForm(@ModelAttribute ContactMessage contactMessage, Model model) {
//        contactMessageService.saveMessage(contactMessage);
//        sendEmail(contactMessage);
//        model.addAttribute("message", "Your message has been sent successfully!");
//        return "contact"; // Redirect to the same contact page or a success page
//    }
//
//    private void sendEmail(ContactMessage contactMessage) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("fidanmammadli28@gmail.com"); // Replace with your email address
//        message.setTo("fidanmammadli28@gmail.com"); // Replace with your email address
//        message.setSubject(contactMessage.getSubject());
//        message.setText("Name: " + contactMessage.getName() + "\nEmail: " + contactMessage.getEmail() + "\nMessage: " + contactMessage.getMessage());
//
//        mailSender.send(message);
//    }
//}
package com.example.edukate.controller;

import com.example.edukate.models.ContactMessage;
import com.example.edukate.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactMessageService contactMessageService;
    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact"; // Ensure this matches your Thymeleaf template name
    }

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute ContactMessage contactMessage, Model model) {
        sendEmail(contactMessage);
        model.addAttribute("message", "Your message has been sent successfully!");
        contactMessageService.saveMessage(contactMessage);
        return "contact"; // Redirect to the same contact page or a success page
    }

    private void sendEmail(ContactMessage contactMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fidanmammadli28@gmail.com"); // Replace with your email address
        message.setTo("fidanmammadli28@gmail.com"); // Replace with your email address
        message.setSubject(contactMessage.getSubject());
        message.setText("Name: " + contactMessage.getName() + "\nEmail: " + contactMessage.getEmail() + "\nMessage: " + contactMessage.getMessage());

        mailSender.send(message);
    }
}

