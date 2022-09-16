package com.smartdubai.yasir.service;

import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.model.Book;

import java.util.List;

public interface BookService {
    public List<BookDTO> getAllBook();

    public Book getBookById(Long id);
    public BookDTO getBookDTOById(Long id);

    public BookDTO save(BookDTO menuDTO);

    public boolean delete(Long id);

    public BookDTO update(BookDTO bookDTO);
}
