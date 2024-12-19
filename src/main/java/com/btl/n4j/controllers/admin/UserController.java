package com.btl.n4j.controllers.admin;

import com.btl.n4j.models.Category;
import com.btl.n4j.models.User;
import com.btl.n4j.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String index(Model model) {

        List<User> userList = this.userService.getAll();
        model.addAttribute("userList", userList);

        return "admin/user/index";
    }

    @GetMapping("/edit-user/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {

        User user = this.userService.findById(id);
        model.addAttribute("user", user);

        return "admin/user/edit";
    }

    @PostMapping("/edit-user")
    public String update(@ModelAttribute("user") User user, @RequestParam("role") String role) {

        User existingUser = userService.findById(user.getUserId());
        existingUser.setRole(role);

        if (this.userService.update(existingUser)) {
            return "redirect:/admin/user";
        } else {
            return "admin/user/edit";
        }
    }
}
