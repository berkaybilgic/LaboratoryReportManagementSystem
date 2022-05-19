package com.example.report.service.concretes;

import com.example.report.entities.concretes.CustomUserDetails;
import com.example.report.entities.concretes.LabTechnicians;
import com.example.report.repository.abstracts.LabTechniciansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private LabTechniciansRepository labTechniciansRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LabTechnicians labTechnicians = labTechniciansRepository.findByUserName(username);
        if (labTechnicians == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(labTechnicians);
    }

}
