package com.example.edukate.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainPageController {

    @GetMapping("")
    public String mainPage() {
        return "dashboard/mainPage/mainPage"; // This should match the name of your HTML file without the extension
    }
}
