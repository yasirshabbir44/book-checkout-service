package com.smartdubai.yasir.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookException extends RuntimeException {

    private Integer exceptionCode;

    public BookException(Integer code, String message) {
        super(message);
        this.exceptionCode = code;
    }
}
