package com.smartdubai.yasir.service.impl;

import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.PromoCodeRepository;
import com.smartdubai.yasir.repository.BookTypeRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {


    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    private final BookService bookService;
    private final BookTypeRepository bookTypeRepository;

    private final PromoCodeRepository promoCodeRepository;

    @Override
    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO) {

        return Optional.ofNullable(checkoutRequestDTO)
                .map(CheckoutRequestDTO::getCheckoutList)
                .map(checkoutDTOList-> {

                    Double finalPriceAfterDiscount = checkoutDTOList.stream().mapToDouble(val->{
                        final Book book = bookService.getBookById(val.getBookId());
                        final Double totalPrice = Double.valueOf(book.getPrice() * val.getQuantity());
                        final Double finalPrice = totalPrice - ( getDiscountOnBook(book) * val.getQuantity());

                        logger.info("Book :" + book.getId() + " Book Price :" + book.getPrice() + " final Price : " + finalPrice);

                        return finalPrice;
                    }).sum();



                    return Optional.ofNullable(checkoutRequestDTO.getPromoCode())
                            .map(val->{
                                return promoCodeRepository.findById(checkoutRequestDTO.getPromoCode())
                                        .map(promoCode -> {
                                           var finalPrice = finalPriceAfterDiscount - (finalPriceAfterDiscount * promoCode.getDiscount());

                                            logger.info("PromoCode = "+ promoCode.getPromoCode() +" , Discount :" +promoCode.getDiscount()
                                                    +" , Final price after Promo Code = "+ finalPrice);
                                           return CheckoutResponseDTO.builder().total(finalPrice).build();
                                        }).orElse(CheckoutResponseDTO.builder().total(finalPriceAfterDiscount).build());

                            })
                                    .orElse(CheckoutResponseDTO.builder().total(finalPriceAfterDiscount).build());




                })
                .orElse(CheckoutResponseDTO.builder().total(0.0d).build());



    }

    private double getDiscountOnBook(Book book) {

        logger.debug("Book Type:" + book.getType());
        return Optional.ofNullable(book)
                .map(val -> bookTypeRepository.findById(book.getType())
                        .map(bookType -> book.getPrice() * bookType.getDiscount())
                        .orElse(0d))
                .orElse(0d);
    }
}
