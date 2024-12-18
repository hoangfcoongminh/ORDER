package com.btl.n4j.controllers.user;

import com.btl.n4j.models.User;
import com.btl.n4j.services.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class CustomerController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/logon")
    public String login() {
        return "logon"; // Trả về trang đăng nhập
    }

    @PostMapping("/logon")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {

        User authenticatedUser = customUserDetailsService.authenticateUser(user.getUsername(), user.getPassword());

        if (authenticatedUser != null) {
            // Lưu thông tin người dùng vào session
            session.setAttribute("loggedInUser", authenticatedUser);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Đăng nhập không thành công");
            return "logon";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Trả về trang đăng ký
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // Lưu người dùng mới vào database
        try {
            customUserDetailsService.registerUser(user);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa thông tin session
        session.removeAttribute("loggedInUser");
        session.invalidate();
        return "redirect:/";
    }

}
