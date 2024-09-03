//package com.thedrugplace.com.DrugPlaceSalesApi.daos;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    public String username;
//    public String password;
//    public String branchCode;
//    public String staffPhone;
//
//    public CustomUserDetails(String username, String password, String branchCode, String staffPhone) {
//        this.username = username;
//        this.password = password;
//        this.branchCode = branchCode;
//        this.staffPhone = staffPhone;
//    }
//
//    @Override
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getPassword() {
//        return "";
//    }
//
//    @Override
//    public String getUsername() {
//        return "";
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
