package com.smartdubai.yasir.service;

import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;

import java.util.List;

public interface CheckoutService {


    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO);
}
