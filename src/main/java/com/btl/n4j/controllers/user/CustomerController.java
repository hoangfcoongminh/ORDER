package com.btl.n4j.controllers.user;

import com.btl.n4j.models.User;
import com.btl.n4j.services.CustomUserDetailsService;
import com.btl.n4j.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/logon")
    public String login(Model model) {

        return "logon"; // Trả về trang đăng nhập
    }

    @PostMapping("/logon")
    public String login(@ModelAttribute User user, Model model) {

        User authenticatedUser = customUserDetailsService.authenticateUser(user.getUsername(), user.getPassword());

        if (authenticatedUser != null) {
            return "redirect:/";
        } else {
            String errorMess = "Tên đăng nhập hoặc mật khẩu không chính xác!";
            model.addAttribute("errorMess", errorMess);
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
            return "redirect:/logon";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/account/info/{id}")
    public String acc_info(Model model, @PathVariable("id") Integer userId) {

        User user = this.userService.getUser(userId);
        model.addAttribute("user", user);

        return "account";
    }

    @PostMapping("/account/info/update")
    public String update_info(@ModelAttribute("user") User user, Model model) {

        String successMess, errorMess;

        if(userService.update(user)) {
            successMess = "Cập nhật thông tin thành công!";
            model.addAttribute("successMess", successMess);
            return "account";
        }
        else {
            errorMess = "Cập nhật không thành công!";
            model.addAttribute("errorMess", errorMess);
            return "account";
        }
    }
}
