package com.smartdubai.yasir.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promo_code")
@AllArgsConstructor
@Getter
public class PromoCode {

    @Id
    private String promoCode;
    private double discount;
}
