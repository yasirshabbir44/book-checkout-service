package com.smartdubai.yasir;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.BookRepository;
import com.smartdubai.yasir.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


    private BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private BookServiceImpl bookService;


    @Before
    public void setup() {
        bookService = new BookServiceImpl(bookRepository);
    }


    @Test
    public void testGetAllBook() {


        List<Book> books = List.of(Book.builder().id(1l)
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build());

        List<BookDTO> bookDTOS = List.of(BookDTO.builder().id(1l)
                .author("Yasir")
                .description("Coding")
                .isbn("111-222-333")
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build());


        when(bookRepository.findAll()).thenReturn(books);

        Assert.assertNotNull(bookService);
        Assert.assertNotNull(bookService.getAllBook());
        Assert.assertEquals(bookDTOS, bookService.getAllBook());
        Assert.assertEquals(1, bookService.getAllBook().size());

    }


    @Test
    public void testGetBookById() {


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

        BookDTO bookDTO = BookDTO.builder().id(1l)
                .author("Yasir")
                .description("Coding")
                .isbn("111-222-333")
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();


        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));

        Assert.assertNotNull(bookService);
        Assert.assertNotNull(bookService.getBookById(1l));
        Assert.assertEquals(book, bookService.getBookById(1l));
        Assert.assertEquals(bookDTO, bookService.getBookDTOById(1l));

    }


    @Test
    public void testSaveBook() {


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

        BookDTO bookDTO = BookDTO.builder()
                .author("Yasir")
                .description("Coding")
                .isbn("111-222-333")
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();


        when(bookRepository.save(any())).thenReturn(book);

        Assert.assertNotNull(bookService);
        Assert.assertNotNull(bookService.save(bookDTO));
        Assert.assertEquals(Long.valueOf(1l), bookService.save(bookDTO).getId());

    }


    @Test
    public void testUpdateBook() {


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

        BookDTO bookDTO = BookDTO.builder().id(1l)
                .author("Yasir")
                .description("Coding")
                .isbn("111-222-333")
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();


        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any())).thenReturn(book);

        Assert.assertNotNull(bookService);
        Assert.assertNotNull(bookService.update(bookDTO));
        Assert.assertEquals(Long.valueOf(1l), bookService.save(bookDTO).getId());

    }


    @Test
    public void testDeleteBook() {

        bookService.delete(1l);
        verify(bookRepository).deleteById(1l);

    }

    @Test(expected = BookException.class)
    public void testDeleteBookError() {
        Mockito.doThrow(EmptyResultDataAccessException.class).when(bookRepository).deleteById(any());
        bookService.delete(1l);

    }

    @Test(expected = BookException.class)
    public void testDeleteBookError1() {
        Mockito.doThrow(IllegalArgumentException.class).when(bookRepository).deleteById(any());
        bookService.delete(1l);

    }

}
