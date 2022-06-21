package com.example.spring_security.controller;

import com.example.spring_security.model.Role;
import com.example.spring_security.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;


    public AdminController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/users")
    public String showUsers(Model model, Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        User admin = userService.getUserByUserName(principal.getName());
        model.addAttribute("adminEmail", admin.getEmail());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "index";
    }

    @GetMapping("/")
    public String showAdminLikeUser(Model model, Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user-admin";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user, Principal principal, Model model, @RequestParam(value = "role_id") long roleId) {

        User admin = userService.getUserByUserName(principal.getName());
        Role role = roleService.getRole(roleId);

        model.addAttribute("adminUsername", admin.getUsername());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRole());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRolesList(role);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "role_id") long roleId) {
        Role role = roleService.getRole(roleId);
        System.err.println(role);
        user.setRolesList(role);
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
