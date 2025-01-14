package com.btl.n4j;

import com.btl.n4j.models.User;
import com.btl.n4j.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;

public class testpass {
    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1"));

        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        User authenticatedUser = customUserDetailsService.authenticateUser("1", "5");

        if (authenticatedUser != null) {
            System.out.println(authenticatedUser);
        } else {
            System.out.println("null");
        }
    }

}
