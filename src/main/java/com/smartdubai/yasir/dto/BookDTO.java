package com.smartdubai.yasir.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {


    private Long id;

    @NotNull
    private String name;

    private String description;


    private String author;
    private String type;
    private Float price;

    private String isbn;

    private Date createDate;

    private Date modifyDate;


}
