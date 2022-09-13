package com.smartdubai.yasir.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Response {
    private Integer code;
    private String message;
    private Object body;
    private Object errorBody;
}
