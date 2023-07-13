package com.smartdubai.yasir.service.impl;

import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.BookTypeRepository;
import com.smartdubai.yasir.repository.PromoCodeRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {


    private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    private final BookService bookService;
    private final BookTypeRepository bookTypeRepository;

    private final PromoCodeRepository promoCodeRepository;


    /**
     * Method return response of Checkout, this method accepting List of BookId and Quantity along with PromoCode.
     * Each PromoCode having different kind of discount like Visa is 10% and Smart is 20%.
     * After calculating the all books in Cart with quantity, it will response with TotalPrice after dicsount in case promo code applied.
    * @Param CheckoutRequestDTO
    * */
    @Override
    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO) {

        return Optional.ofNullable(checkoutRequestDTO)
                .map(CheckoutRequestDTO::getCheckoutList)
                .map(checkoutDTOList -> {

                    Double finalPriceAfterDiscount = checkoutDTOList.stream().mapToDouble(val -> {
                        final Book book = bookService.getBookById(val.bookId());
                        final Double totalPrice = Double.valueOf(book.getPrice() * val.quantity());
                        final Double finalPrice = totalPrice - (getDiscountOnBook(book) * val.quantity());

                        logger.info("Book :" + book.getId() + " Book Price :" + book.getPrice() + " final Price : " + finalPrice);

                        return finalPrice;
                    }).sum();


                    return Optional.ofNullable(checkoutRequestDTO.getPromoCode())
                            .map(val -> promoCodeRepository.findById(checkoutRequestDTO.getPromoCode())
                                    .map(promoCode -> {
                                        var finalPrice = finalPriceAfterDiscount - (finalPriceAfterDiscount * promoCode.getDiscount());

                                        logger.info("PromoCode = " + promoCode.getPromoCode() + " , Discount :" + promoCode.getDiscount()
                                                + " , Final price after Promo Code = " + finalPrice);
                                        return CheckoutResponseDTO.builder().totalPrice(finalPrice).build();
                                    }).orElse(CheckoutResponseDTO.builder().totalPrice(finalPriceAfterDiscount).build()))
                            .orElse(CheckoutResponseDTO.builder().totalPrice(finalPriceAfterDiscount).build());


                })
                .orElse(CheckoutResponseDTO.builder().totalPrice(0.0d).build());


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
