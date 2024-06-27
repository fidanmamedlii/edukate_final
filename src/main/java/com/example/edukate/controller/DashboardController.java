package com.example.edukate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/admin/messages")
    public String getMessagesPage() {
        return "dashboard/replies/replies";
    }
}