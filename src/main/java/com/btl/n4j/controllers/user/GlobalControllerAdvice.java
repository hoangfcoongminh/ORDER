package com.btl.n4j.controllers.user;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("principal")
    public Principal getPrincipal(Principal principal) {
        // Trả về đối tượng chứa thông tin user hiện tại
        return principal;
    }
}
