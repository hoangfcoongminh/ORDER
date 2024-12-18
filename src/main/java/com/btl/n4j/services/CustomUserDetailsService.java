package com.btl.n4j.services;

import com.btl.n4j.models.CustomUserDetails;
import com.btl.n4j.models.User;
import com.btl.n4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username đã tồn tại");
        }
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("CUSTOMER"); // Thiết lập role cho người dùng

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOptionalByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng!"));

        return new CustomUserDetails(user, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList("ROLE_USER");  // Hoặc các quyền khác
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
