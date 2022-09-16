package com.smartdubai.yasir.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder(toBuilder = true)
@Table(name = "promo_code")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PromoCode {

    @Id
    private String promoCode;
    private double discount;
}
