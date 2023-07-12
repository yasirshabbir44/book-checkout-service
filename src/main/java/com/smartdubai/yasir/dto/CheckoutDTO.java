package com.smartdubai.yasir.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record CheckoutDTO(Long bookId,Long quantity) {}
