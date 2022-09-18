package com.smartdubai.yasir.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookType {

    @Id
    private String type;
    private double discount;
}
