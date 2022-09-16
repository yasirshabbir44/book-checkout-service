package com.smartdubai.yasir.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder(toBuilder = true)
@Table(name = "book_classification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookClassification {

    @Id
    private String classification;
    private double discount;
}
