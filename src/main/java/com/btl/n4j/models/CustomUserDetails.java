package com.btl.n4j.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public User getUser() {
        return this.user;
    }

    public String getFullName() {
        return user.getFullName();
    }

    public String getGender() {
        return user.getGender();
    }

    public Date getDateOfBirth() {
        return user.getDateOfBirth();
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
