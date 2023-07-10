package com.smartdubai.yasir.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder(toBuilder = true)
public record CheckoutDTO(Long bookId,Long quantity) {}
