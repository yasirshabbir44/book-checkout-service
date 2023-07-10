package com.smartdubai.yasir.controller;


import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponseDTO checkout(@Valid @RequestBody CheckoutRequestDTO checkoutRequestDTO) {
        return checkoutService.checkout(checkoutRequestDTO);
    }

}
