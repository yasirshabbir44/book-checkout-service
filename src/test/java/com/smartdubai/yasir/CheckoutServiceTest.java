package com.smartdubai.yasir;


import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.model.BookType;
import com.smartdubai.yasir.model.PromoCode;
import com.smartdubai.yasir.repository.BookTypeRepository;
import com.smartdubai.yasir.repository.PromoCodeRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.service.impl.CheckoutServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckoutServiceTest {


    private BookService bookService = Mockito.mock(BookService.class);
    private BookTypeRepository bookTypeRepository = Mockito.mock(BookTypeRepository.class);
    private PromoCodeRepository promoCodeRepository = Mockito.mock(PromoCodeRepository.class);
    private CheckoutServiceImpl checkoutService;


    @Before
    public void setup() {

        checkoutService = new CheckoutServiceImpl(bookService, bookTypeRepository, promoCodeRepository);
    }


    @Test
    public void testCheckoutBooks() {
        BookType bookType = new BookType("fiction", 0.10);
        when(bookTypeRepository.findById("fiction")).thenReturn(Optional.of(bookType));


        Book book = Book.builder().id(1l)
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();

        when(bookService.getBookById(any())).thenReturn(book);
        PromoCode bookPromoCode = new PromoCode("SMART", 0.20);
        when(promoCodeRepository.findById("SMART")).thenReturn(Optional.of(bookPromoCode));


        CheckoutRequestDTO requestDTO = CheckoutRequestDTO.builder()
                .promoCode("SMART")
                .checkoutList(List.of(CheckoutDTO.builder().bookId(1l).quantity(2l).build())).build();

        CheckoutResponseDTO checkoutResponseDTO = checkoutService.checkout(requestDTO);
        Assert.assertNotNull(checkoutResponseDTO);
        Assert.assertTrue(checkoutResponseDTO.getTotal() == 288d);
    }
}
