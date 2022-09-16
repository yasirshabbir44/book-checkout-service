package com.smartdubai.yasir.controller;


import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.smartdubai.yasir.util.ResponseCode.*;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBook() {
        return bookService.getAllBook();
    }



    @GetMapping("/{bookId}")
    public BookDTO getBook(@PathVariable("bookId") long bookId) {
        return bookService.getBookDTOById(bookId);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok(Response.builder().code(DELETE_BOOK_CODE).message(DELETE_BOOK_MSG).build());

    }


    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(Response.builder()
                .code(CREATE_BOOK_CODE).message(CREATE_BOOK_MSG)
                        .body(bookService.save(bookDTO))
                .build());
    }


    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(Response.builder()
                .code(CREATE_BOOK_CODE).message(CREATE_BOOK_MSG)
                .body(bookService.update(bookDTO))
                .build());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookException.class)
    public ResponseEntity handleValidationExceptions(BookException ex) {
        return ResponseEntity
                .badRequest()
                .body(Response.builder().message(ex.getMessage()).code(ex.getExceptionCode()).build());
    }
}
