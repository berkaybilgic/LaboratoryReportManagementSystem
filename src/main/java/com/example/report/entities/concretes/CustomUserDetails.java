package com.example.report.entities.concretes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private LabTechnicians labTechnicians;

    public CustomUserDetails(LabTechnicians labTechnicians) {
        this.labTechnicians = labTechnicians;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(labTechnicians.getRoles()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return labTechnicians.getPassword();
    }

    @Override
    public String getUsername() {
        return labTechnicians.getUserName();
    }

    public String getIdentityNumber() {
        return labTechnicians.getIdentificationNumber();
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
