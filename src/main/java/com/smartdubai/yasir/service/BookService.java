package com.smartdubai.yasir.service;

import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.model.Book;

import java.util.List;

public interface BookService {
    public List<BookDTO> getAllBanner();

    List<BookDTO> getAllBanner(Integer pageNumber, Integer pageSize);

    public Book getBookById(Long id);
    public BookDTO getBookDTOById(Long id);

    public BookDTO save(BookDTO menuDTO);

    public void delete(Long id);

    public BookDTO update(BookDTO bookDTO);
}
