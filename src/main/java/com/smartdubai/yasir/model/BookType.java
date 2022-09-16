package com.smartdubai.yasir.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder(toBuilder = true)
@Table(name = "book_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookType {

    @Id
    private String type;
    private double discount;
}
