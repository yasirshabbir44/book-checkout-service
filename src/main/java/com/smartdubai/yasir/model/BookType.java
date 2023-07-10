package com.smartdubai.yasir.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
