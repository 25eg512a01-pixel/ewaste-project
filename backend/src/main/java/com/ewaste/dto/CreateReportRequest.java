package com.ewaste.dto;
import jakarta.validation.constraints.*;
public record CreateReportRequest(@NotBlank @Size(max=80) String category,@NotBlank @Size(max=1000) String description,@Positive double quantityKg,@NotBlank @Size(max=500) String address,@Size(max=500) String notes) {}
