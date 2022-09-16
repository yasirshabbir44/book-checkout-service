package com.smartdubai.yasir.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Integer code;
    private String message;
    private Object body;
    private Object errorBody;
}
