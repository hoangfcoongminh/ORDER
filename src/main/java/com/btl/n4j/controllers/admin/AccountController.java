package com.btl.n4j.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }
}
