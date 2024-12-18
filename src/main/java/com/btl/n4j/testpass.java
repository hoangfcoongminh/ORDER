package com.btl.n4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;

public class testpass {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1"));

    }

}
