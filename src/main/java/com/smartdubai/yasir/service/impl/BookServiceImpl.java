package com.smartdubai.yasir.service.impl;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.repository.BookRepository;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.util.ResponseCode;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<BookDTO> getAllBanner() {
       return  bookRepository.findAll().stream().map(menu -> modelMapper.map(menu , BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAllBanner(Integer pageNumber, Integer pageSize) {
        return  bookRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id").descending())).stream().map(menu -> modelMapper.map(menu , BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookDTOById(Long id) {

        return bookRepository.findById(id).map(brand -> modelMapper.map(brand,BookDTO.class))
                .orElseThrow(() -> new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));
    }

    @Override
    public Book getBookById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));
    }

    @Override
    public BookDTO save(BookDTO menuDTO) {
        Book book = modelMapper.map(menuDTO, Book.class);
        book = bookRepository.save(book);

       return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void delete(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (IllegalArgumentException | EmptyResultDataAccessException exception) {
            throw new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG);
        }
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {

        return bookRepository.findById(bookDTO.getId())
                .map(book -> modelMapper.map(bookDTO, Book.class))
                .map(book -> bookRepository.save(book))
                .map(book -> modelMapper.map(book, BookDTO.class))
                .orElseThrow(()->new BookException(ResponseCode.DATA_NOT_FOUND_CODE, ResponseCode.DATA_NOT_FOUND_MSG));

    }
}
