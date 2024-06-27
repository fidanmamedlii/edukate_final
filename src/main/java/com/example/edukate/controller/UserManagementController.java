package com.example.edukate.controller;

import com.example.edukate.models.Role;
import com.example.edukate.models.User;
import com.example.edukate.service.RoleService;
import com.example.edukate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

@Controller
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        List<User> users = userService.findAllUsers();
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "dashboard/user-management";
    }

    @PostMapping("/admin/users/update-role")
    public String updateUserRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        Optional<User> userOptional = userService.findById(userId);
//        Optional<Role> roleOptional=roleService.findById(roleId);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
//            Role roles=roleOptional.get();
            Role role = roleService.findById(roleId).orElse(null);
            if (role != null) {
                user.getRoles().clear();
                user.getRoles().add(role);
                userService.save(user);
            }
        }
        return "redirect:/admin/users";
    }
}
