package com.smartdubai.yasir.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
public record CheckoutResponseDTO(Double total){}