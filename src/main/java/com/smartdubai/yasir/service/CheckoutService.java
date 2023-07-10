package com.smartdubai.yasir.service;

import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;

public interface CheckoutService {


    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO);
}
