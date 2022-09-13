package com.smartdubai.yasir.exception;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookException extends RuntimeException {

    private Integer exceptionCode;
    public BookException(Integer code, String message){
        super(message);
        this.exceptionCode = code;
    }
}
