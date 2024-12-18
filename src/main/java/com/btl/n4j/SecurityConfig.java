package com.btl.n4j;

import com.btl.n4j.services.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException {
                // Kiểm tra vai trò của người dùng và chuyển hướng tương ứng
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

                if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                    response.sendRedirect("/admin");
                } else {
                    response.sendRedirect("/");
                }
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(customUserDetailsService)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(  auth -> auth
                    .requestMatchers("/", "/field", "/fieldtype/**", "/search", "/detail-field/**", "/logon", "/register").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/logon")
                    .loginProcessingUrl("/logon")
                    .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")   // URL đăng xuất
                    .logoutSuccessUrl("/") // Sau khi đăng xuất thành công, chuyển về trang đăng nhập
                    .invalidateHttpSession(true) // Hủy session khi đăng xuất
                    .clearAuthentication(true)  // Xóa thông tin xác thực khi đăng xuất
            )
            .exceptionHandling(handling -> handling
                    .accessDeniedPage("/") // Chỉ định trang lỗi khi không có quyền
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
            );
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/back-end/**", "/front-end/**", "/assets/**", "upload/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
