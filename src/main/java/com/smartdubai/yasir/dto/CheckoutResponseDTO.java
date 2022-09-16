package com.smartdubai.yasir.dto;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class CheckoutResponseDTO {

   private Double total;

}
