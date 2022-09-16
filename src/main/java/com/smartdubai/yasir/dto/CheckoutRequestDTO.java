package com.smartdubai.yasir.dto;


import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckoutRequestDTO {


    private String promoCode;
    private List<CheckoutDTO> checkoutList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutRequestDTO that = (CheckoutRequestDTO) o;
        return Objects.equals(promoCode, that.promoCode) && Objects.equals(checkoutList, that.checkoutList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promoCode, checkoutList);
    }
}
