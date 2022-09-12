package com.smartdubai.yasir.smartdubaitest.controller;


import com.smartdubai.yasir.smartdubaitest.dto.BookDTO;
import com.smartdubai.yasir.smartdubaitest.exception.BookException;
import com.smartdubai.yasir.smartdubaitest.service.BookService;
import com.smartdubai.yasir.smartdubaitest.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.smartdubai.yasir.smartdubaitest.util.ResponseCode.*;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBook() {
        return ResponseEntity.ok(Response.builder().code(GET_ALL_BOOK_CODE).message(GET_ALL_BOOK_MSG).body(bookService.getAllBanner()).build());
    }

    @GetMapping
    public ResponseEntity<?> getAllBook(@RequestParam() Integer pageNumber,@RequestParam() Integer pageSize) {
        return ResponseEntity.ok(Response.builder().code(GET_ALL_BOOK_CODE).message(GET_ALL_BOOK_MSG).body(bookService.getAllBanner(pageNumber,pageSize)).build());
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<?> getProduct(@PathVariable("bookId") long bookId) {

        return ResponseEntity.ok(Response
                .builder()
                .code(GET_BOOK_CODE)
                .message(GET_BOOK_MSG)
                .body(bookService.getBookById(bookId))
                .build());
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok(Response.builder().code(DELETE_BOOK_CODE).message(DELETE_BOOK_MSG).build());

    }


    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody BookDTO bookDTO) {

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
