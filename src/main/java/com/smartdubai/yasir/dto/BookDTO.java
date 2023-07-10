package com.smartdubai.yasir.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder(toBuilder = true)
public record BookDTO (Long id, @NotNull String name,String description,String author,String type,Float price,String isbn) {}