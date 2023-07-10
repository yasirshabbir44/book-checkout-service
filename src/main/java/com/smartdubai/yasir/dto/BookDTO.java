package com.smartdubai.yasir.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
