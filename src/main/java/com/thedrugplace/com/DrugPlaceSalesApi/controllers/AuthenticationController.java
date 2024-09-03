package com.thedrugplace.com.DrugPlaceSalesApi.controllers;

import com.thedrugplace.com.DrugPlaceSalesApi.beans.security.JwtTokenProvider;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.auth.AuthRequest;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.auth.AuthResponse;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final StaffService staffService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService, StaffService staffService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.staffService = staffService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest.getUsername() + ":" + authRequest.getPassword());
        try {
            // Authenticate the user using the provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // If authentication is successful, generate a JWT token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(userDetails);

            // Retrieve additional user details
            String username = userDetails.getUsername();
            String name = getUserFullName(userDetails).orElse(username);
            String role = getUserRole(userDetails);
            String branchCode = getUserBranchCode(userDetails);
            String staffPhone = getUserStaffPhone(userDetails);

            // Return the token and user details as part of the response
            return ResponseEntity.ok(new AuthResponse(token, username, name, role, branchCode, staffPhone));
        } catch (AuthenticationException e) {
            logger.error("Login failed for user: {}", authRequest.getUsername(), e);
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        try {
            // Validate the JWT token
            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.extractUsername(token);
                logger.info("Token validated successfully for user: {}", username);
                return ResponseEntity.ok("Token is valid");
            } else {
                logger.warn("Token validation failed");
                return ResponseEntity.status(401).body("Token is invalid or expired");
            }
        } catch (Exception e) {
            logger.error("Error during token validation", e);
            return ResponseEntity.status(401).body("Token validation error");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        try {
            // Validate the existing token before refreshing
            if (jwtTokenProvider.validateToken(token)) {
                // Refresh the token
                String refreshedToken = jwtTokenProvider.refreshToken(token);
                String username = jwtTokenProvider.extractUsername(refreshedToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                String name = getUserFullName(userDetails).orElse(username);
                String role = getUserRole(userDetails);
                String branchCode = getUserBranchCode(userDetails);
                String staffPhone = getUserStaffPhone(userDetails);

                logger.info("Token refreshed successfully for user: {}", username);
                return ResponseEntity.ok(new AuthResponse(refreshedToken, username, name, role, branchCode, staffPhone));
            } else {
                logger.warn("Token refresh failed due to invalid token");
                return ResponseEntity.status(401).body("Token is invalid or expired");
            }
        } catch (Exception e) {
            logger.error("Error during token refresh", e);
            return ResponseEntity.status(401).body("Failed to refresh token");
        }
    }

    // Helper method to fetch user's full name (replace with actual logic)
    private Optional<String> getUserFullName(UserDetails userDetails) {
        return Optional.ofNullable(userDetails.getUsername()); // Default to username for now
    }

    // Helper method to fetch user's role
    private String getUserRole(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .findFirst()
                .map(Object::toString)
                .orElse("ROLE_USER"); // Default to ROLE_USER if no role found
    }

    // Helper method to fetch user's branch code (replace with actual logic)
    private String getUserBranchCode(UserDetails userDetails) {

        return staffService.getStaffByUsername(userDetails.getUsername()).getBranch().getBranch_code(); // Placeholder value
    }

    // Helper method to fetch user's staff phone (replace with actual logic)
    private String getUserStaffPhone(UserDetails userDetails) {
        return staffService.getStaffByUsername(userDetails.getUsername()).getStaff_phone(); // Placeholder value
    }
}
