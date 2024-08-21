package com.example.springbootcleanarchitecture.module.org.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Org {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;
}
