package com.smartdubai.yasir.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class CheckoutResponseDTO {

    private Double total;

}
