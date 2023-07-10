package com.smartdubai.yasir.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "promo_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PromoCode {

    @Id
    private String promoCode;
    private double discount;
}
