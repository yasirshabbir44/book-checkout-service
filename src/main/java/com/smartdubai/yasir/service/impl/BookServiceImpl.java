package com.smartdubai.yasir.service.impl;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.BookRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.util.ResponseCode;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    @Override
    public List<BookDTO> getAllBook() {
        return bookRepository.findAll().stream().map(this::map).toList();
    }


    @Override
    public BookDTO getBookDTOById(Long id) {

        return bookRepository.findById(id).map(this::map)
                .orElseThrow(() -> new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));
    }

    @Override
    public Book getBookById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = this.map(bookDTO);
        book = bookRepository.save(book);

        return this.map(book);
    }

    @Override
    public boolean delete(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (IllegalArgumentException | EmptyResultDataAccessException exception) {
            throw new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG);
        }
        return true;
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {

        return bookRepository.findById(bookDTO.id())
                .map(book -> this.map(bookDTO))
                .map(book -> bookRepository.save(book))
                .map(this::map)
                .orElseThrow(() -> new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));

    }


    private BookDTO map(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .name(book.getName())
                .type(book.getType())
                .description(book.getDescription())
                .author(book.getAuthor())
                .build();
    }


    private Book map(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.id())
                .isbn(bookDTO.isbn())
                .price(bookDTO.price())
                .name(bookDTO.name())
                .type(bookDTO.type())
                .description(bookDTO.description())
                .author(bookDTO.author())
                .build();
    }

}
