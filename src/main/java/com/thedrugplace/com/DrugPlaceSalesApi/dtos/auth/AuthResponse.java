package com.thedrugplace.com.DrugPlaceSalesApi.dtos.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {
    private String token;
    private String username;
    private String name;
    private String role;
    private String branchCode;
    private String staffPhone;
}
