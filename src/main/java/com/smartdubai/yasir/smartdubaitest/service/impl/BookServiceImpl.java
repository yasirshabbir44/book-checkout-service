package com.smartdubai.yasir.smartdubaitest.service.impl;


import com.smartdubai.yasir.smartdubaitest.dto.BookDTO;
import com.smartdubai.yasir.smartdubaitest.model.Book;
import com.smartdubai.yasir.smartdubaitest.repository.BookRepository;
import com.smartdubai.yasir.smartdubaitest.exception.BookException;
import com.smartdubai.yasir.smartdubaitest.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.smartdubai.yasir.smartdubaitest.util.ResponseCode.DATA_NOT_FOUND_CODE;
import static com.smartdubai.yasir.smartdubaitest.util.ResponseCode.DATA_NOT_FOUND_MSG;

@AllArgsConstructor
@Component
public class BookServiceImpl implements BookService {


    private final BookRepository menuRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<BookDTO> getAllBanner() {
       return  menuRepository.findAll().stream().map(menu -> modelMapper.map(menu , BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookDTO getMenuById(Long id) {

        return menuRepository.findById(id).map(brand -> modelMapper.map(brand,BookDTO.class))
                .orElseThrow(() -> new BookException(DATA_NOT_FOUND_CODE,DATA_NOT_FOUND_MSG));
    }

    @Override
    public BookDTO save(BookDTO menuDTO) {
        Book book = modelMapper.map(menuDTO, Book.class);
        book = menuRepository.save(book);

       return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void delete(Long id) {
        try {
            menuRepository.deleteById(id);
        } catch (IllegalArgumentException | EmptyResultDataAccessException exception) {
            throw new BookException(DATA_NOT_FOUND_CODE,DATA_NOT_FOUND_MSG);
        }
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {

        Book book = modelMapper.map(bookDTO, Book.class);
        book = menuRepository.save(book);

        return modelMapper.map(book, BookDTO.class);
    }
}
