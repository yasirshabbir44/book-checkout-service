package com.smartdubai.yasir.service.impl;

import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final BookService bookService;

    @Override
    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO) {
        AtomicReference<Float> totalDiscount = new AtomicReference<>(0f);

        final Double total = checkoutRequestDTO.getCheckoutList().stream()
                .mapToDouble(val -> {
                    final Book book = bookService.getBookById(val.getBookId());
                    final Double totalPrice = Double.valueOf(book.getPrice() * val.getQuantity());

                    return Optional.ofNullable(checkoutRequestDTO.getPromoCode())
                            .filter(promoCode -> promoCode.equalsIgnoreCase("SMART"))
                            .map(promoCode -> {
                                final String type = Optional.ofNullable(book.getType()).orElse("");

                                if (type.equalsIgnoreCase("fiction")) {
                                    totalDiscount.set((float) (totalDiscount.get() + (totalPrice * .1)));
                                    return totalPrice - (totalPrice * .1);
                                }
                                return totalPrice;
                            }).orElse(totalPrice);
                })
                .sum();


        final var response = CheckoutResponseDTO.builder()
                .checkoutDTOList(checkoutRequestDTO.getCheckoutList())
                .totalPrice(total.floatValue())
                .totalDiscount(totalDiscount.get())
                .build();


        return response;

    }
}
