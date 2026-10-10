package com.ewaste.dto;
import jakarta.validation.constraints.*; import java.time.LocalDateTime;
public record CreatePickupRequest(@NotNull Long reportId,@NotNull LocalDateTime scheduledAt) {}
