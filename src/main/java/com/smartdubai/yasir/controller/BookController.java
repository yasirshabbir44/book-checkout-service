package com.smartdubai.yasir.controller;


import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.service.BookService;
import com.smartdubai.yasir.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.smartdubai.yasir.util.ResponseCode.*;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Booking Controller",description = "Controller dealing with Book Operation")
@AllArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @Operation(summary = "Method return list of Books")
    public List<BookDTO> getAllBook() {
        return bookService.getAllBook();
    }


    @GetMapping("/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @Operation(summary = "Method return Book")
    public BookDTO getBook(@PathVariable("bookId") long bookId) {
        return bookService.getBookDTOById(bookId);
    }


    @DeleteMapping("/{bookId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @Operation(summary = "Method delete the Books")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok(Response.builder().code(DELETE_BOOK_CODE).message(DELETE_BOOK_MSG).build());

    }


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @Operation(summary = "Method create the Books")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(Response.builder()
                .code(CREATE_BOOK_CODE).message(CREATE_BOOK_MSG)
                .body(bookService.save(bookDTO))
                .build());
    }


    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "500", description = "Internal Error.")
    })
    @Operation(summary = "Method update the Books")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDTO bookDTO) {

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
