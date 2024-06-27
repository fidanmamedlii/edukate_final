////package com.example.edukate.controller.Admin;
////
////import com.example.edukate.dtos.replydto.ContactCommentDto;
////import com.example.edukate.models.ContactMessage;
////import com.example.edukate.repositories.ContactMessageRepository;
////import com.example.edukate.service.ContactMessageService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("/admin/messages")
////public class ContactMessageAdminController {
////
////    @Autowired
////    private ContactMessageRepository messageRepository;
////
////    @Autowired
////    private JavaMailSender mailSender;
////
////    @Autowired
////    private ContactMessageService messageService;
////
////    public ContactMessageAdminController(ContactMessageRepository messageRepository, JavaMailSender mailSender, ContactMessageService messageService) {
////        this.messageRepository = messageRepository;
////        this.mailSender = mailSender;
////        this.messageService = messageService;
////    }
////
////    @GetMapping("/admin/messages")
////    public String getMessagesPage() {
////        return "dashboard/replies/replies";
////    }
////
////    @GetMapping
////    public List<ContactMessage> getMessages() {
////        return messageService.getAllMessages();
////    }
////
////    @PostMapping
////    public String saveMessage(@ModelAttribute ContactCommentDto message) {
////        messageService.saveMessage(message);
////        return "redirect:/admin/messages";
////    }
////
////    @PostMapping("/reply/{id}")
////    public ContactMessage replyToMessage(@PathVariable Long id, @RequestBody String reply) {
////        return messageService.replyToMessage(id, reply);
////    }
////}
//package com.example.edukate.controller.Admin;
//
//import ch.qos.logback.core.model.Model;
//import com.example.edukate.dtos.replydto.ContactCommentDto;
//import com.example.edukate.models.ContactMessage;
//import com.example.edukate.service.ContactMessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/messages")
//public class ContactMessageAdminController {
//
//    @Autowired
//    private ContactMessageService messageService;
//
//    @GetMapping("/admin/messages")
//    public String getMessagesPage() {
//        return "dashboard/replies/replies";
//    }
//    @GetMapping
//    public List<ContactMessage> getMessages() {
//        return messageService.getAllMessages();
//    }
//
//    @PostMapping
//    public String saveMessage(@ModelAttribute ContactCommentDto message) {
//        messageService.saveMessage1(message);
//        return "redirect:/admin/messages";
//    }
//
//    @PostMapping("/reply/{id}")
//    public ContactMessage replyToMessage(@PathVariable Long id, @RequestBody String reply) {
//        return messageService.replyToMessage(id, reply);
//    }
//}
//

package com.example.edukate.controller.Admin;

import com.example.edukate.dtos.replydto.ContactCommentDto;
import com.example.edukate.models.ContactMessage;
import com.example.edukate.repositories.ContactMessageRepository;
import com.example.edukate.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")

public class ContactMessageAdminController {


    @Autowired
    private ContactMessageService messageService;

    @PostMapping
    public String saveMessage(@ModelAttribute ContactMessage message) {


        messageService.saveMessage(message);
        return "redirect:/messages";
    }


    @GetMapping
    public List<ContactMessage> getMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/reply/{id}")
    public ContactMessage replyToMessage(@PathVariable Long id, @RequestBody String reply) {
        return messageService.replyToMessage(id, reply);
    }
}
