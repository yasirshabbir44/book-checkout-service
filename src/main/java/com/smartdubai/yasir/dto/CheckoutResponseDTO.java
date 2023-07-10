package com.smartdubai.yasir.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record CheckoutResponseDTO(Double totalPrice){}