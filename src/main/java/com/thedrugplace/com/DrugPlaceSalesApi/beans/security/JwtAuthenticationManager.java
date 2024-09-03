package com.thedrugplace.com.DrugPlaceSalesApi.beans.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtAuthenticationManager {
    private final UserDetailsService userdetailsService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationManager(UserDetailsService userdetailsService, PasswordEncoder passwordEncoder) {
        this.userdetailsService = userdetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationManager createAuthenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userdetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
}
