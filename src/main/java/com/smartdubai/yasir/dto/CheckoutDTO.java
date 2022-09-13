package com.smartdubai.yasir.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckoutDTO {

    private Long bookId;
    private Long quantity;


}
