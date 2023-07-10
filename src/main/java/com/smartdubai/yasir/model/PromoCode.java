package com.smartdubai.yasir.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
