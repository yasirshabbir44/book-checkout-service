package com.smartdubai.yasir.dto;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckoutResponseDTO {


   private Double total;



}
