package com.smartdubai.yasir.exception;


import lombok.*;

@Getter
@NoArgsConstructor
public class BookException extends RuntimeException {

    private Integer exceptionCode;
    public BookException(Integer code, String message){
        super(message);
        this.exceptionCode = code;
    }
}
