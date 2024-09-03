package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService implements UserDetailsService {

    private final StaffRepository staffRepository;

    @Autowired
    public AuthenticationService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the Staff member by username
        Staff staff = staffRepository.findByUsername(username);

        if (staff == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Directly return the user details without encoding the password
        return new User(staff.getUsername(), staff.getPassword(), Collections.emptyList());
    }
}
