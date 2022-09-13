package com.smartdubai.yasir.controller;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.service.BookService;
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


    private final BookService bookService;


    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody List<CheckoutDTO> checkoutDTOList) {

        final Double total = checkoutDTOList.stream()
                .mapToDouble(val->{
                    BookDTO bookDTO = bookService.getBookById(val.getBookId());
                    return bookDTO.getPrice() * val.getQuantity();
                }).sum();

        final var response = CheckoutResponseDTO.builder()
                .checkoutDTOList(checkoutDTOList)
                .totalPrice(total.floatValue())
                .build();


        return ResponseEntity.ok(Response.builder()
                .code(CHECKOUT_CODE).message(CHECKOUT_MSG)
                        .body(response)
                .build());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookException.class)
    public ResponseEntity handleValidationExceptions(BookException ex) {
        return ResponseEntity
                .badRequest()
                .body(Response.builder().message(ex.getMessage()).code(ex.getExceptionCode()).build());
    }
}
