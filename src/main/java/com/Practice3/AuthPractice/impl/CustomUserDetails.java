package com.Practice3.AuthPractice.impl;

import com.Practice3.AuthPractice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;


    @JsonIgnore
    private String password;

    private boolean is2faEnabled;

    private Collection<? extends GrantedAuthority> authorities;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIs2faEnabled() {
        return is2faEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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


    public CustomUserDetails() {
    }

    public CustomUserDetails(Long id, String username, String email, String password, boolean is2faEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(User user){

        List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));

        return new CustomUserDetails(user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.isTwoFactorEnabled(),
                authority
        ) ;
    }

}
