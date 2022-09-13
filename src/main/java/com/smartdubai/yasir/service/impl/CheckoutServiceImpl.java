package com.smartdubai.yasir.service.impl;

import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.BookRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final BookService bookService;
    @Override
    public CheckoutResponseDTO checkout(List<CheckoutDTO> checkoutDTOList) {
        final Double total = checkoutDTOList.stream()
                .mapToDouble(val->{
                    Book book = bookService.getBookById(val.getBookId());
                    return book.getPrice() * val.getQuantity();
                }).sum();

        final var response = CheckoutResponseDTO.builder()
                .checkoutDTOList(checkoutDTOList)
                .totalPrice(total.floatValue())
                .build();


        return response;

    }
}
