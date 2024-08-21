package com.example.springbootcleanarchitecture.module.org.domain.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateOrgCommand {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;
}
