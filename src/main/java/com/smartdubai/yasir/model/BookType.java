package com.smartdubai.yasir.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
