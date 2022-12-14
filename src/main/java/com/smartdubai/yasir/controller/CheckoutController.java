package com.smartdubai.yasir.controller;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.CheckoutService;
import com.smartdubai.yasir.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.smartdubai.yasir.util.ResponseCode.*;

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
