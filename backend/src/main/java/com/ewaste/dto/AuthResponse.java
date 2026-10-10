package com.ewaste.dto;
import com.ewaste.model.AppUser;
public record AuthResponse(String token, UserView user) { public record UserView(Long id,String name,String email,String role,int points) { public static UserView from(AppUser u){return new UserView(u.getId(),u.getName(),u.getEmail(),u.getRole().name(),u.getPoints());} } }
