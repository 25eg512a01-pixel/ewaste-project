package com.ewaste.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank @Size(max=100) String name,@Email @NotBlank String email,@NotBlank @Size(min=8,max=100) String password) {}
