package app.controllers;

import app.persistance.entity.Role;
import app.persistance.entity.User;
import app.service.AdminService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin_control")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.allUsers());
        return "userList";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model)
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(@RequestParam String userName, @RequestParam Map<String, Object> form, @RequestParam("userId") User user){
        user.setUsername(userName);
        userService.saveUser(adminService.setUserRoles(user, form));
        return "redirect:/admin_control";
    }
}
