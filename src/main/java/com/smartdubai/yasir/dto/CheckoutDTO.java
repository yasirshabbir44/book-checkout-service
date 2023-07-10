package com.smartdubai.yasir.dto;

import lombok.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckoutDTO {

    private Long bookId;
    private Long quantity;


}
