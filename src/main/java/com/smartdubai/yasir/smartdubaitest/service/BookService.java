package com.smartdubai.yasir.smartdubaitest.service;

import com.smartdubai.yasir.smartdubaitest.dto.BookDTO;

import java.util.List;

public interface BookService {
    public List<BookDTO> getAllBanner();

    public BookDTO getMenuById(Long id);

    public BookDTO save(BookDTO menuDTO);

    public void delete(Long id);

    public BookDTO update(BookDTO bookDTO);
}
